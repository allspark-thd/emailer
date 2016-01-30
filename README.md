Request Emailer
===
Email requests for services to interested parties.  
 
#Setup 

```
cf cups smtp-server -p '{"hostname": "smtp.example.com", "username":"mail-user", "password": "mail-password"}'
cf push
```


#Usage

`POST` to the `/send` api with the required information in the body. 

```
{
    "spaceGuid": "123456-space",
    "appId": "123452-the-app",
    "accessRequirementsRepo": "https://some/github/repo",
    "desiredVaultUrl": "/vault_url/v1/secret/service-instance-id"
}
```

A success is indicated by a response code of `200` and a response body of `OK`

The recipient gets the following boiler plate email with the values from 
above filled in 

> Subject: 
> New Request for access to a datasource

> Body: 
> A request for access to a new datasource has been created db requirements 
> url: https://some/github/repo
> app id: 123452-the-appspace guid: 123456-space
> vault url: /vault_url/v1/secret/service-instance-id

The recipient of the email can be set in application.yaml (and is defaulted to 
something that **will not work** in real life but works for the unit/local 
tests). It's sensible to override this via the environment by setting an env var
named `recipient`. 

For example: 

```
cf set-env emailer recipient jkruck@example.com
cf restage emailer
```

#Local Testing

I run `postfix` on port 25 and use `mailq` to test that stuff goes where I need 
it to on a mac. 