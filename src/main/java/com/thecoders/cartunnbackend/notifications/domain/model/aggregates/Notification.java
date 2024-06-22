package com.thecoders.cartunnbackend.notifications.domain.model.aggregates;

import com.thecoders.cartunnbackend.notifications.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    public Notification() {
        this.type = Strings.EMPTY;
        this.description = Strings.EMPTY;
    }

    public Notification(String type, String description) {
        this();
        this.type = type;
        this.description = description;
    }

    public Notification(CreateNotificationCommand command) {
        this();
        this.type = command.type();
        this.description = command.description();
    }

    public Notification updateInformation(String type, String description) {
        this.type = type;
        this.description = description;
        return this;
    }
}