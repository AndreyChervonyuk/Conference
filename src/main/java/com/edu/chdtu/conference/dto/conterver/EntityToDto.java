package com.edu.chdtu.conference.dto.conterver;


import com.edu.chdtu.conference.dto.EventPermissionDto;
import com.edu.chdtu.conference.model.DefaultEventPermission;
import com.edu.chdtu.conference.model.EventPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EntityToDto {

    public static EventPermissionDto getEventPermissionDto(List<EventPermission> permissions) {
        return new EventPermissionDto(
                permissions.stream()
                        .collect(Collectors.groupingBy(permission -> permission.getPermission().getCategory().getName()))
        );
    }

    public static EventPermissionDto getDefaultEventPermissionDto(List<DefaultEventPermission> permissions) {
        List<EventPermission> eventPermissions = new ArrayList<>();

        for (DefaultEventPermission permission : permissions) {
            eventPermissions.add(new EventPermission(permission.getPermission(), permission.getUserGroup()));
        }

        return new EventPermissionDto(
                eventPermissions.stream()
                        .collect(Collectors.groupingBy(permission -> permission.getPermission().getCategory().getName()))
        );
    }
}
