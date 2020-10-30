# flow-mdh-platform-api-service

Boomi MDH Platform API Flow Service

Boomi Boomi MDH API Flow Service	1
Overview	1
Service URL	1
Features	2
GitHub URL	2
Known limitations	2
Installing the MDH API Service	3
MDH API Service Configuration Values	3


The Boomi MDH API Flow Service enables direct access to the Boomi Integration and Boomi API Manager API. 
Overview

The Boomi MDH API provides direct access to API Actions. This service makes it possible to build no-code MDH operations dashboards for Boomi environments. For more information please refer to the API documentation:

Boomi MDH Platform API: https://help.boomi.com/bundle/hub/page/r-mdm-Platform_API.html



Service URL
The service endpoint url is:
Editor’s note, the demo service is available at http://flowmdmapiservice-env-2.us-east-2.elasticbeanstalk.com:8080
https://services.manywho.com/api/boomiMDHapi/2

Features
The Boomi MDH service forms part of the following features within flow:
Message: This service feature allows you to connect to Boomi API actions. Examples include actions for executing a process or canceling a process execution


GitHub URL


Known limitations
Pagination not supported
Query Transactions
Query Staged Entities
Golden Record data returned as XML text
Get Quarantine Entry
Get Golden Record for Source Entity

Installing the MDH API Service
To use the Boomi MDH API service, you will first need to install the service into your flow tenant.
Before you begin
Users of the service will need the appropriate privileges within the Boomi Platform. At minimum, the API Access privilege is required to use the service. 

For more information regarding Boomi user and permission management, please refer to: https://help.boomi.com/bundle/MDH_platform/page/r-atm-User_management.html


Installing the Boomi MDH API service
On the Home tab, select Services from the main menu.
Click Install Service.
Select the 'Boomi MDH API' service from the Install Service drop-down menu.
Editors note: currently a Custom Service is required but I assume that will change when this service is hosted by Flow
Click Continue.
Enter a name for the service in the Name field, 'Boomi MDH API service' for example.
Click Set Configuration Values.
Specify the Configuration values for your database configuration. See Boomi MDH API Service Configuration Values for details on the required values for this service.
Click Continue.
If you have configured the service correctly with your database, the Service Installed page is displayed. The Boomi MDH API service is now installed.
MDH API Service Configuration Values

Name
Type
Required?
Description
Account
String

Specify the ID of your Boomi MDH Account.
Use Identity Service Credentials
Boolean

Indicates whether to use the username and password entered when logging with the MDH API Identity service. If the Identity service is not used, set this value to $False and specify the username and password in the fields below.
Username



Optional: If the Identity service is not used, you must set the MDH API username. This can be a standard platform credential or an API Token. For more info regarding API tokens please refer to: https://help.boomi.com/bundle/integration/page/int-MDH_API_and_Partner_API_authentication.html
Password



Optional: If the Identity service is not used, you must set the MDH API password or API token
Server Public Certificate



Specify a URL Server Certificate for server verification.
Note: Using SSL and a Server Public Certificate is recommended best practice when implementing the SQL service, particularly if the connection between the SQL database and the SQL service is not in your private network.





