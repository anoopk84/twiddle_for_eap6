#!/bin/bash
#
# Twiddle for JBoss EAP 6.
#
# Fork of Twiddle included in JBoss EAP 5, and modified to support EAP 6
#     by Kenjiro NAKAYAMA <nakayamakenjiro at gmail dot com>
#
# Copyright 2008, Red Hat Middleware LLC, and individual contributors,
#
# This is free software; you can redistribute it and/or modify it
# under the terms of the GNU Lesser General Public License as
# published by the Free Software Foundation; either version 2.1 of
# the License, or (at your option) any later version.
#
# This software is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
# License along with this software; if not, write to the Free
# Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
# 02110-1301 USA, or see the FSF site: http://www.fsf.org.
#

DIRNAME=`dirname $0`
PROGNAME=`basename $0`
JBOSS_CLIENT_JAR=jboss-client.jar

if [ ! -e $DIRNAME/$JBOSS_CLIENT_JAR ]; then
    echo "ERROR: jboss-client.jar is not exist in the release directory. Please copy and put it from JBoss distro"
    echo ""
    echo "       eg) cp \$JBOSS_HOME/bin/client/jboss-client.jar $DIRNAME"
    echo ""
    exit 1
fi


# Setup the JVM
if [ "x$JAVA_HOME" != "x" ]; then
    JAVA=$JAVA_HOME/bin/java
else
    JAVA="java"
fi

cd $DIRNAME

export CLASSPATH=$(pwd)/$JBOSS_CLIENT_JAR:$CLASSPATH
export CLASSPATH=$(pwd)/twiddle-twiddle.jar:$CLASSPATH

exec "$JAVA" \
    -Dprogram.name="$PROGNAME" \
     console.twiddle.Twiddle "$@"
