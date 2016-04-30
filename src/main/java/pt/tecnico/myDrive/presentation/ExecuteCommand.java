package pt.tecnico.myDrive.presentation;

import pt.tecnico.myDrive.service.ExecuteFileService;

/**
 * Created by jp_s on 4/30/2016.
 */
public class ExecuteCommand extends MdCommand{

    public ExecuteCommand(MdShell sh){super(sh, "do", "execute the file given by a path with the args");}

    @Override
    public void execute(String[] args) {
        String path = args[1];
        String arg = "";
        for(int i = 2; i < args.length; i++){
            arg += " ";
            arg += args[i];
        }
     //   ExecuteFileService executeFileService = new ExecuteFileService(((MdShell) shell()).getCurrentToken(), path, arg);
     //   executeFileService.execute();
    }
}

