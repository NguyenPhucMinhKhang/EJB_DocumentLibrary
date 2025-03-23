**1. Prerequisites**
  + JDK 8 (jdk1.8.0_202)
  + Visual Studio Code Extensions:
    - Community Server Connectors
    - Maven for Java
  + Installing GlassFish 5 on Community Server Connectors
  + Install the Community Server Connectors and Maven for Java extension in Visual Studio Code

**2. Setting the setting.js:** "rsp-ui.rsp.java.home": "C:\\Program Files\\Java\\jdk-24" (the jdk version must be at least 20)

**3. Edit the GlassFish server:**
  + From the "Server" tab, start the server and right click to Create a New Server.
  + Right click to glassfish5, choose Edit server
  + Config like this:
    - Domain: domain1 (default)
    - Port: 8080 (default)
    - Admin Port: 4848 (default)
    - Add one line: "vm.install.path": "C:\\Program Files\\Java\\jdk1.8.0_202", (change your path to jdk1.8 there)
  
 **4. Edit the JAVA_HOME: must be jdk1.8.**

  **5. Build the maven project:**
  + From the tab "Maven", choose the maven project -> Run Maven -> package.
   ![image](https://github.com/user-attachments/assets/cfa4a166-004e-48d7-af5b-31316a8840f9)

  **6. Start the server:**
  + From the Explorer, choose the WAR file -> Run on Server (localhost:8080)
