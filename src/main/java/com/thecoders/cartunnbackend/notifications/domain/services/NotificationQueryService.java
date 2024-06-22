package com.thecoders.cartunnbackend.notifications.domain.services;

import com.thecoders.cartunnbackend.notifications.domain.model.aggregates.Notification;
import com.thecoders.cartunnbackend.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.thecoders.cartunnbackend.notifications.domain.model.queries.GetNotificationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {
    Optional<Notification> handle(GetNotificationByIdQuery query);

    List<Notification> handle(GetAllNotificationsQuery query);
}
