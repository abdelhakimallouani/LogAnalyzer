package ma.youcode.lineperm.model;

import ma.youcode.lineperm.enums.Permission;


public class LinFile {
    private final Long id;
    private final String name;
    private final Long ownerId;
    private Permission permission;


    public LinFile(Long id,String name, Long ownerId,Permission permission) {
        this.id=id;
        this.name = name;
        this.ownerId = ownerId;
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Permission getPermission() {
        return permission;
    }


    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    
}

