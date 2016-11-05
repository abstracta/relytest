/*
 * Copyright (C) 2016 MS
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package relytest.web.client;

import relytest.ui.Constants;
import relytest.ui.PropertiesMgr;

/**
 *
 * @author MS
 */
public abstract class RelytestClient {

    protected String SERVER_IP;
    protected String SERVER_PORT;

    public RelytestClient() {
        PropertiesMgr p = new PropertiesMgr();
        SERVER_IP = p.getValue(Constants.KEY_SERVER_IP);
        SERVER_PORT = p.getValue(Constants.KEY_SERVER_PORT);
    }
}
