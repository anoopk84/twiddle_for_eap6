/*
 * Twiddle for JBoss EAP 6.
 *
 * Fork of Twiddle included in JBoss EAP 5, and modified to support EAP 6
 *     by Kenjiro NAKAYAMA <nakayamakenjiro at gmail dot com>
 *
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors,
 *     Jason Dillon <jason at planet57 dot com>,
 *     <Scott.Stark at jboss dot org>
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package console.twiddle.command;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerInfoCommand extends MBeanServerCommand {
  public static final int UNKNOWN = 0;
  public static final int DEFAULT_DOMAIN = 1;
  public static final int MBEAN_COUNT = 2;
  public static final int LIST_NAMES = 3;

  private int mode = UNKNOWN;

  public ServerInfoCommand() {
    super("serverinfo", "Get information about the MBean server");
  }

  public void displayHelp() {
    PrintWriter out = context.getWriter();

    out.println(desc);
    out.println();
    out.println("usage: " + name + " [options]");
    out.println();
    out.println("options:");
    out.println("    -d, --domain    Get the default domain");
    out.println("    -c, --count     Get the MBean count");
    out.println("    -l, --list      List the MBeans");
    out.println("    --              Stop processing options");
  }

  private void processArguments(final String[] args) throws CommandException {
    if (log.isDebugEnabled())
    {
      log.debug("processing arguments: " + Stream.of(args).collect(Collectors.joining(",")));
    }
    if (args.length == 0) {
      throw new CommandException("Command requires arguments");
    }

    String sopts = "-:dcl";
    LongOpt[] lopts = {
      new LongOpt("domain", LongOpt.NO_ARGUMENT, null, 'd'),
      new LongOpt("count", LongOpt.NO_ARGUMENT, null, 'c'),
      new LongOpt("list", LongOpt.NO_ARGUMENT, null, 'l'), };

    Getopt getopt = new Getopt(null, args, sopts, lopts);
    getopt.setOpterr(false);

    int code;
    while ((code = getopt.getopt()) != -1) {
      switch (code) {
        case ':':
          throw new CommandException("Option requires an argument: "
                                     + args[getopt.getOptind() - 1]);

        case '?':
          throw new CommandException("Invalid (or ambiguous) option: "
                                     + args[getopt.getOptind() - 1]);

          // non-option arguments
        case 1:
          throw new CommandException("Unused argument: "
                                     + getopt.getOptarg());

        case 'd':
          mode = DEFAULT_DOMAIN;
          break;

        case 'c':
          mode = MBEAN_COUNT;
          break;
        case 'l':
          mode = LIST_NAMES;
          break;
      }
    }
  }

  public void execute(String[] args) throws Exception {
    processArguments(args);

    PrintWriter out = context.getWriter();
    MBeanServerConnection server = getMBeanServer();

    // mode should be valid, either invalid arg or no arg

    switch (mode) {
      case DEFAULT_DOMAIN:
        out.println(server.getDefaultDomain());
        break;

      case MBEAN_COUNT:
        out.println(server.getMBeanCount());
        break;

      case LIST_NAMES:
        ObjectName all = new ObjectName("*:*");
        Set names = server.queryNames(all, null);
        Iterator iter = names.iterator();
        while (iter.hasNext())
          out.println(iter.next());
        break;

      default:
        throw new IllegalStateException("invalid mode: " + mode);
    }

    out.flush();
  }
}
