Twiddle for EAP 6
===========================

Twiddle for EAP 6 is fork of Twiddle included in JBoss EAP 5, and modified to support EAP 6 by Kenjiro Nakayama.

**NOTE**
Twiddle for EAP 6 can change access port by `-a` option, it is different from previous Twiddle.

Supported Version
---------------------------
* EAP 6 and AS7/Wildfly

How to use?
--------------------------

Step1. git clone

    git clone https://github.com/nak3/twiddle_for_eap6.git

Step2. run build.sh

    cd twiddle_for_eap6
    ./build.sh

Step3. copy jboss-cli.sh from your JBoss EAP 6 (JBoss AS 7)

    cp JBOSS_HOME/bin/client/jboss-client.jar ./release

Step4. Now, it's ready! Run twiddle!

    cd release
    ./twiddle.sh <options>

(or you can move to somewhere you want to use it)

    cp -r release /SOMEWHERE/PATH/twiddle
	cd /SOMEWHERE/PATH/twiddle
	./twiddle.sh <options>

Usage example
--------------------------

See [twiddle-cheatsheet](https://github.com/nak3/twiddle_for_eap6/blob/master/docs/twiddle-cheatsheet.md)

eg. Datasource

How many connections my datasource(eg. ExampleDS) is using now?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get jboss.as:subsystem=datasources,data-source=ExampleDS,statistics=pool ActiveCount
    ActiveCount=0

How many connections my datasource(eg. ExampleDS) can use now? 

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get jboss.as:subsystem=datasources,data-source=ExampleDS,statistics=pool AvailableCount
    AvailableCount=30

How can I set maxPoolsize of my dagtasource (eg. ExampleDS)?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD set jboss.as:subsystem=datasources,data-source=ExampleDS  maxPoolSize 20
    maxPoolSize=20
