/*
 * Copyright (c) 2014 Gnat008
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.gnat008.zombietracker.commands;

import me.gnat008.zombietracker.ZTMain;
import org.bukkit.entity.Player;

public class TrackerSubCommand {

    private enum ValidArgs {ON, OFF}

    private TrackerSubCommand() {

    }

    public static void parseCommand(ZTMain plugin, Player player, String... args) {
        if (args.length == 1) {
            if (plugin.getManager().getActiveTrackers().containsKey(player.getUniqueId())) {
                plugin.getManager().removeTracker(player);
                plugin.getPrinter().printToPlayer(player, "Tracker disabled!", false);

            } else {
                plugin.getManager().createTracker(player);
                plugin.getPrinter().printToPlayer(player, "Tracker enabled!", false);
            }
        } else if (args.length == 2) {
            ValidArgs vargs;
            try {
                vargs = ValidArgs.valueOf(args[1].toUpperCase());
            } catch (Exception notEnum) {
                plugin.getPrinter().printToPlayer(player, "Invalid usage! Use /zt tracker [on|off]", true);
                return;
            }

            switch (vargs) {
                case ON:
                    if (plugin.getManager().getActiveTrackers().containsKey(player.getUniqueId())) {
                        plugin.getPrinter().printToPlayer(player, "Your tracker is already enabled!", true);
                        return;
                    }

                    plugin.getManager().createTracker(player);
                    plugin.getPrinter().printToPlayer(player, "Tracker enabled!", false);
                    return;

                case OFF:
                    if (plugin.getManager().getActiveTrackers().containsKey(player.getUniqueId())) {
                        plugin.getManager().removeTracker(player);
                        plugin.getPrinter().printToPlayer(player, "Tracker disabled!", false);
                    } else {
                        plugin.getPrinter().printToPlayer(player, "Your tracker is already disabled!", true);
                    }
            }
        } else {
            plugin.getPrinter().printToPlayer(player, "Invalid usage! Use /zt tracker [on|off]", true);
        }
    }
}
