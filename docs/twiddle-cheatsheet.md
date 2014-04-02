Twiddle for EAP 6 Cheat Sheet
==============================

This cheat sheet is just some examples to use Twiddle for EAP 6. I hope they can be a help.

System
-----------------
How to execute Full GC?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD invoke java.lang:type=Memory gc
    'null'

**NOTE** This result is 'null'. Check GC log in your server with adding GC log option like this.

    JAVA_OPTS="$JAVA_OPTS -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCTimeStamps"


Application
----------------

How to redeploy my application ?

	./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD invoke jboss.as:deployment=$APPLICATION_NAME redeploy
    'null'

How to undeploy my application ?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD invoke jboss.as:deployment=$APPLICATION_NAME undeploy
    'null'

How to deploy my application ?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD invoke jboss.as:deployment=$APPLICATION_NAME deploy
    'null'

How many Http sessions is active my application.

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get  jboss.as:deployment=$APPLICATION_NAME,subsystem=web activeSessions
    activeSessions=0


Datasource
-----------------
How many connections my datasource(eg.ExampleDS) is using now?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get jboss.as:subsystem=datasources,data-source=ExampleDS,statistics=pool ActiveCount
    ActiveCount=0

How many connections my datasource(eg.ExampleDS) can use now?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get jboss.as:subsystem=datasources,data-source=ExampleDS,statistics=pool AvailableCount
    AvailableCount=30

How can I set maxPoolsize of my dagtasource(eg.ExampleDS)?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD set jboss.as:subsystem=datasources,data-source=ExampleDS  maxPoolSize 20
    maxPoolSize=20


Web Container
-----------------

It depends on using executor or not.


##### Not using executor situation

**NOTE** If you want to get from jboss.web:*, you need to add following java option.

     JAVA_OPTS="$JAVA_OPTS -Dorg.apache.tomcat.util.ENABLE_MODELER=true"

How many active threads in my web container?

     ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get jboss.web:type=ThreadPool,name=http--$HOST_IP-$PORT_NUM currentThreadsBusy
    currentThreadsBusy=0

##### Using executor situation

How many active threads in my web container?

    ./twiddle.sh -s $HOST_NAME -u $USER_NAME -p $PASSWORD get jboss.as:subsystem=threads,bounded-queue-thread-pool=$EXECUTER-NAME
	currentThreadCount=0
