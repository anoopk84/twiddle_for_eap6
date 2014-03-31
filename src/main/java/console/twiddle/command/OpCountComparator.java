/*
 * Twiddle for JBoss EAP 6.
 *
 * Fork of Twiddle included in JBoss EAP 5, and modified to support EAP 6
 *     by Kenjiro NAKAYAMA <nakayamakenjiro at gmail dot com>
 *
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors,
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

import java.util.Comparator;

public class OpCountComparator implements Comparator
{
  public int compare(Object o1, Object o2)
  {
    MBeanOp op1 = (MBeanOp) o1;
    MBeanOp op2 = (MBeanOp) o2;
    int compare = op1.getName().compareTo(op2.getName());
    if( compare == 0 )
      compare = op1.getArgCount() - op2.getArgCount();
    return compare;
  }
}
