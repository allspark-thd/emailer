package com.homedepot.entity;

public class EmailDetails {
    private String spaceGuid;
    private String appId;
    private String accessRequirementsRepo;
    private String desiredVaultUrl;

    public String getSpaceGuid() {
        return spaceGuid;
    }

    public void setSpaceGuid(String spaceGuid) {
        this.spaceGuid = spaceGuid;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAccessRequirementsRepo() {
        return accessRequirementsRepo;
    }

    public void setAccessRequirementsRepo(String accessRequirementsRepo) {
        this.accessRequirementsRepo = accessRequirementsRepo;
    }

    public String getDesiredVaultUrl() {
        return desiredVaultUrl;
    }

    public void setDesiredVaultUrl(String desiredVaultUrl) {
        this.desiredVaultUrl = desiredVaultUrl;
    }
}
