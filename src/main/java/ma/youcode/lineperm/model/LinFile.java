package ma.youcode.lineperm.model;

import ma.youcode.lineperm.enums.Permission;


public class LinFile {
    private Long id;
    private String name;
    private Long ownerId;
    private Permission permission;


    public LinFile(String name, Long ownerId,Permission permission) {
        this.name = name;
        this.ownerId = ownerId;
        this.permission = permission;
    }
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

