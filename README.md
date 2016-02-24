# dirigible 

[![Build Status](https://travis-ci.org/eclipse/dirigible.svg)](https://travis-ci.org/eclipse/dirigible)

**Dirigible** is an Integrated Development Environment as a Service (IDEaaS) for dynamic applications. It provides both development tools and runtime environment.

<p align="center">
  <img src="https://github.com/eclipse/dirigible/blob/master/logo/dirigible-logo-symbol.png" width="60%" alt="dirigible logo"/>
</p>

> Enjoy Programming Like Never Before

From the end user's perspective (developer), Dirigible runs directly in the browser, therefore does not require any downloads or installations.

From the service provider's perspective (PaaS/SaaS), Dirigible packs all required components in a self-contained software bundle that can be deployed in any Java-based web server, such as Tomcat, Jetty, JBoss.

Dirigible supports access to RDBMS via  JDBC. Currently supported versions for RDBMS are HANA 1.x, MaxDB, Sybase ASE (experimental), and PostgreSQL (experimental).

The project started as an internal SAP initiative to address the extension and adaption use-cases related to SOA and Enterprise Services.

- [Try](#try)
- [Get Started](#get-started)
	- [Download](#download)
	- [Build](#build)
	- [Deploy](#deploy)
		- [Instant Trial](#instant-trial) 
		- [HANA Cloud Platform](#hana-cloud-platform)
		- [Tomcat](#tomcat)
		- [Eclipse](#eclipse)
		- [CloudFoundry](#cloudfoundry)
- [Additional Information](#additional-information)
	- [License](#license)
	- [Contributors](#contributors)
	- [References](#references)
		
## Instant Trial

You can try the sandbox instance to have a quick look on the functionality you are interested [http://trial.dirigible.io](http://trial.dirigible.io).

## Get Started

### Download

The "fast-track" - you can download the precompiled binaries produced from the Hudson builds from [http://wiki.eclipse.org/Dirigible/Downloads](http://wiki.eclipse.org/Dirigible/Downloads) and skip the build section.

Nevertheless, we highly recommend building the binaries from source in order to have all experimental features that are not available in the releases.

### Build

##### Prerequisites

- [Git](http://git-scm.com/)
- [Maven 3.0.x](http://maven.apache.org/docs/3.0.5/release-notes.html)


### `--- Use Maven 3.0.x! ---` ###


##### Steps

1. Clone the [project repository](https://github.com/eclipse/dirigible.git) or [download the latest release](https://github.com/eclipse/dirigible/archive/master.zip).
2. Go to the `org.eclipse.dirigible/org.eclipse.dirigible.parent` folder in the file system. Open a command window there.
3. Build the project with:

        mvn clean install

The build should pass successfully. The produced WAR files under target sub-folder `org.eclipse.dirigible/org.eclipse.dirigible.parent/releng` are ready to be deployed. There are separated deployable artifacts (WAR files) depending on the usage type.

### Deploy


#### Trial

Trial package combines the deployable artifacts of Dirigible along with the Tomcat web container and Derby database. It is useful for quick exploration of Dirigible features.

##### Steps

1. Build the project with:

	mvn clean install -P trial
	
The build should pass successfully. Find the produced produced executable JAR file under target sub-folder at `org.eclipse.dirigible/org.eclipse.dirigible.parent/releng`.

2. Run with:

	java -jar dirigible-all-tomcat-trial-executable.jar
	
3. Open a web browser and go to:

        http://localhost:8080/dirigible/services/ui/anonymous.html	

#### HANA Cloud Platform

Deploy on [HANA Cloud Platform](https://account.hana.ondemand.com/) with the [Cloud SDK](https://tools.hana.ondemand.com/#cloud).

##### Prerequisites

- [HANA Cloud Platform SDK](https://tools.hana.ondemand.com/#cloud)

##### Steps

1. Go to the `neo-java-web-sdk-2.xxx/tools` SDK folder.
2. Deploy with command:

        neo deploy --account <your_account> --application <application_name> --user <your_user> --host <target_landscape_host> --source <source_directory> --password <your_password>

3. Start with command:

        neo start --account <your_account> --application <application_name> --user <your_user> --host <target_landscape_host> --password <your_password> -y

4. Go to https://account.hanatrial.ondemand.com/cockpit at Authorizations section. Add Developer and Operator role to your user which gives you full access to all features.

#### Tomcat

The Tomcat specific WAR files can be deployed on [Tomcat](http://tomcat.apache.org/) web container. In this case the built-in Derby database is used.

More information about how to deploy on Tomcat can be found [here](http://tomcat.apache.org/tomcat-7.0-doc/appdev/deployment.html).

##### Steps

1. For simplicity rename the WAR `dirigible-all-tomcat-xxx.war` to `dirigible.war`.
2. Configure Users store:

        <tomcat-users>
                <role rolename="Developer"/>
                <role rolename="Operator"/>
                <role rolename="Everyone"/>
                <user username="dirigible" password="dirigible" roles="Developer,Operator,Everyone"/>
        </tomcat-users>

4. Open a web browser and go to:

        http://localhost:8080/dirigible

4. Login with dirigible/dirigible.

#### Eclipse

The IDE part can be run directly via Eclipse. This is useful when testing new features during development.

##### Prerequisites

- [Maven 3.0.x](http://maven.apache.org/)
- [Eclipse IDE](https://www.eclipse.org/)

##### Steps

1. Run preparation command

        mvn eclipse:eclipse 

2. Import the project as existing Maven project into your local Eclipse environment.
3. Go to project `org.eclipse.dirigible/org.eclipse.dirigible.parent/platform/org.eclipse.dirigible.platform.target` and open the file `org.eclipse.dirigible.platform.base.target` using the Target Editor.
4. Click on the `Set as Target Platform` link and wait until the required bundles get synchronized.
5. Use `dirigible-local.launch` file for `Run As` configuration.
6. Open a web browser and go to:

        http://localhost:8080/dirigible


#### CloudFoundry

##### Prerequisites

- [CloudFoundry Cli](http://docs.cloudfoundry.org/devguide/installcf/install-go-cli.html)

##### Steps

1. Login to CloudFoundry Platform with:

		cf login -a [CloudFoundry Platform Host]

2. Deploy on the CloudFoundry supported Cloud Platform with:

		cf push dirigible -p [path to the target directory]/dirigible-all-tomcat-xxx.war -b https://github.com/dirigible-io/java-buildpack

3. Open a web browser and go to:

        http://dirigible.[CloudFoundry Platform Host]/

4. Login with user `dirigible` and password `dirigible` which are set by default in the custom buildpack used above.

#### Docker

##### Prerequisites

- [Install Docker](https://docs.docker.com/engine/installation/)

##### Steps
      
1. Pull the already built container from Quay.io

        docker pull quay.io/delchevn/dirigible223
        
2. Start the container

        docker run -p 8888:8080 -p quay.io/delchevn/dirigible223

3. Open a web browser and go to:

        http://[docker container ip]:8888/
        
4. Optionally you can enhance and customize the Dockerfile from [here](https://github.com/eclipse/dirigible/blob/master/org.eclipse.dirigible/org.eclipse.dirigible.parent/releng/docker/Dockerfile)

## Additional Information

### License

This project is copyrighted by [SAP SE](http://www.sap.com/) and is available under the [Eclipse Public License v 1.0](https://www.eclipse.org/legal/epl-v10.html). See [LICENSE.txt](LICENSE.txt) and [NOTICE.txt](NOTICE.txt) for further details.

### Contributors

If you like to contribute to Dirigible, please read the [Contributor's guide](CONTRIBUTING.md).

### References

- Project Home
[http://www.dirigible.io](http://www.dirigible.io)

- Help Portal
[http://help.dirigible.io](http://help.dirigible.io) 

- Simple Samples
[http://samples.dirigible.io](http://samples.dirigible.io)

- Trial Instance
[http://trial.dirigible.io](http://trial.dirigible.io)

- Forum
[https://www.eclipse.org/forums/index.php/m/1688357/](https://www.eclipse.org/forums/index.php/m/1688357/)

- Mailing List
[https://dev.eclipse.org/mailman/listinfo/dirigible-dev](https://dev.eclipse.org/mailman/listinfo/dirigible-dev)

- Bugzilla
[https://bugs.eclipse.org/bugs/describecomponents.cgi?product=Dirigible](https://bugs.eclipse.org/bugs/describecomponents.cgi?product=Dirigible)

### Update Sites

- [http://download.eclipse.org/dirigible/nightly/p2/bridge/](http://download.eclipse.org/dirigible/nightly/p2/bridge/)
- [http://download.eclipse.org/dirigible/nightly/p2/external/](http://download.eclipse.org/dirigible/nightly/p2/external/)
- [http://download.eclipse.org/dirigible/nightly/p2/ide/](http://download.eclipse.org/dirigible/nightly/p2/ide/)
- [http://download.eclipse.org/dirigible/nightly/p2/lib/](http://download.eclipse.org/dirigible/nightly/p2/lib/)
- [http://download.eclipse.org/dirigible/nightly/p2/repository/](http://download.eclipse.org/dirigible/nightly/p2/repository/)
- [http://download.eclipse.org/dirigible/nightly/p2/runtime/](http://download.eclipse.org/dirigible/nightly/p2/runtime/)
- [http://download.eclipse.org/dirigible/nightly/p2/rcp/](http://download.eclipse.org/dirigible/nightly/p2/rcp/)
