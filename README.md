Twiddle for EAP 6
===========================

Twiddle for EAP 6 is fork of Twiddle included in EAP 5 to modifed to support EAP 6 by Kenjiro Nakayama. 

*CAUTION*
SOFTWARE ASSUMES NO RESPONSIBILITY FOR ERRORS OR OMISSIONS IN THE INFORMATION OR SOFTWARE*

Supported Version
---------------------------
* EAP 6 and JBoss AS 7

How to use?
--------------------------

1. git clone 

    `git clone https://github.com/nak3/twiddle_for_eap6.git`

2. run build.sh

eg)
    
    cd release
    ./build.sh

3. copy jboss-cli.sh from your JBoss EAP 6 (JBoss AS 7)

    `cp JBOSS_HOME/bin/client/jboss-client.jar release`

4. Now, it's ready! Run twiddle!

    `./RELEASE_DIR/twiddle.sh <options>`


Usage example
--------------------------

eg) Datasource

How many connections my datasource(ExampleDS) is using now?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get jboss.as:subsystem=datasources,data-source=ExampleDS,statistics=pool ActiveCount
    ActiveCount=0

How many connections my datasource(ExampleDS) can use now? 

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get jboss.as:subsystem=datasources,data-source=ExampleDS,statistics=pool AvailableCount
    AvailableCount=30

How can I set maxPoolsize of my dagtasource(ExampleDS)?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD set jboss.as:subsystem=datasources,data-source=ExampleDS  maxPoolSize 20
    maxPoolSize=20
