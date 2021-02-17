package parts.lost.mc.scriptexecutor.kotlin.commands.commandseautomation

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import parts.lost.mc.scriptexecutor.kotlin.constructs.BasicHelpNotes
import parts.lost.mc.scriptexecutor.kotlin.interfaces.SubCommand
import parts.lost.mc.scriptexecutor.kotlin.storage.Storage

object NextRun : SubCommand {
    override val name = "nextrun"
    override val helpNotes = BasicHelpNotes(
        this,
        "<automated script ID>",
        "Provides the next scheduled run of an automated script."
    )

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size != 1)
            sender.sendMessage("${ChatColor.RED}This command takes one argument.")
        else {
            val automatedScript = Storage.automatedScripts.find { it.scriptID == args[0] }
            //TODO
            if (automatedScript == null) {
                sender.sendMessage("${ChatColor.RED}Invalid script ID provided.")
                return true
            }

        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> = when(args.size) {
        2 -> Storage.automatedScripts.map { it.scriptID }.toMutableList()
        else -> MutableList(0) { "" }
    }
}