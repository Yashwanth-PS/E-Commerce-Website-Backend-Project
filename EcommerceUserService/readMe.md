**HomeWork:**

- Connect to DB, Get the system up and running, Check all APIs for User and Roles are working
- When a user logs in, remove the earlier session's token data and make the session ENDED, then do the login flow.
  
**Fundamental APIs for auth are already given by Spring Security OAuth2:**
1. /login
2. /logout
3. /signup
4. /validate -> To validate the token from Resource Service to Auth Service
5. /register -> Registering a new Client [Client - The service which will rely on Auth Service for Authentication]

**Central AuthService:**

- User Login -> Customer Login
- Dev Portal Provide --> API Key for Auth
- Central AuthService [OAuth] - All the Micro-Services will be Client to this Central Service

**Employee Login - Developer Login:**

- SSO -> Single Sign On -> Single Way to Sign In Everyone
  - Central Login Portal
- LDAP Based Authentication - Hardware Authentication
  - Active Directories -> Folder inside Computer Only the Admin User of the Device can Access
    - User Roles
    - Password
    - Client Key
  - Comparing the Active Directory Information (i.e. Hardware Information) with the service
- LDAP (Lightweight Directory Access Protocol) is a software protocol for enabling anyone to locate 
data about organizations, individuals and other resources such as files and devices in a network 
whether on the public internet or a corporate intranet.