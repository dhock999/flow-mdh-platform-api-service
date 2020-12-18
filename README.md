Boomi MDH Platform API Flow Service

# Boomi Boomi MDH API Flow Service

The Boomi MDH API Flow Service enables direct access to the Boomi Integration and Boomi API Manager API. 
Overview

The Boomi MDH API provides direct access to API Actions. This service makes it possible to build no-code MDH operations dashboards for Boomi environments. For more information please refer to the API documentation:

Boomi MDH Platform API: https://help.boomi.com/bundle/hub/page/r-mdm-Platform_API.html

## Features
The Boomi MDH service forms part of the following features within flow:
Message: This service feature allows you to connect to Boomi API actions. Examples include actions for executing a process or canceling a process execution


## Known limitations
* Pagination not supported
* Query Transactions
* Query Staged Entities
* Golden Record data returned as XML text
 * Get Quarantine Entry
* Get Golden Record for Source Entity

## Installing the MDH API Service
To use the Boomi MDH API service, you will first need to install the service into your flow tenant.
Before you begin
Users of the service will need the appropriate privileges within the Boomi Platform. At minimum, the API Access privilege is required to use the service. 

For more information regarding Boomi user and permission management, please refer to: https://help.boomi.com/bundle/MDH_platform/page/r-atm-User_management.html

## Installing the Boomi MDH API service
1. On the Home tab, select Services from the main menu.
1. Click Install Service.
1. Select the 'Boomi MDH API' service from the Install Service drop-down menu.
1. Click Continue.
1. Enter a name for the service in the Name field, 'Boomi MDH API service' for example.
1. Click Set Configuration Values.
1. Specify the Configuration values for your database configuration. See Boomi MDH API Service Configuration Values for details on the required values for this service.
1. Click Continue.

If you have configured the service correctly, the Service Installed page is displayed. The Boomi MDH API service is now installed.
MDH API Service Configuration Values

Name | Type | Required | Description 
---- | ---- | -------- | -----------
Account | String | X | Specify the ID of your Boomi Atomsphere Account.
Username | String | X | If the Identity service is not used, you must set the Atomsphere API username. This can be a standard platform credential or an API Token. For more info regarding API tokens please refer to: https://help.boomi.com/bundle/integration/page/int-AtomSphere_API_and_Partner_API_authentication.html
Password | String | X | If the Identity service is not used, you must set the Atomsphere API password or API token
Server Public Certificate |  |  | Specify a URL Server Certificate for server verification.

## Contributing

Contributions are welcome to the project - whether they are feature requests, improvements or bug fixes! Refer to [CONTRIBUTING.md](CONTRIBUTING.md) for our contribution requirements.

## License

This SDK is released under the [MIT License](http://opensource.org/licenses/mit-license.php).


