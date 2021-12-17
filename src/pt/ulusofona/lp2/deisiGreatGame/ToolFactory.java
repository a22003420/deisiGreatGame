package pt.ulusofona.lp2.deisiGreatGame;
//imports
import java.util.List;

/*
Represents a ToolFactory in a board tile
 */
public class ToolFactory extends Tile
{
    //###########
    //ATTRIBUTES

    /*
    Tool type ID to Produce
     */
    protected final int toolTypeId;

    //################
    //Constructor

    public ToolFactory(int toolTypeId){
        this.toolTypeId = toolTypeId;
    }

    //################
    //Methods

    /*
    Return Produced Tool Title
    */
    @Override
    protected String getTitle(){
        ToolSingletonFactory toolFactory = ToolSingletonFactory.getInstance();
        Tool tool = toolFactory.getTool(this.toolTypeId);

        if(tool == null) {
            return "";
        }

        return tool.getTitle();
    }

    /*
    Return Produced Tool Image
     */
    @Override
    protected String getImagePng(){
        ToolSingletonFactory toolFactory = ToolSingletonFactory.getInstance();
        Tool tool = toolFactory.getTool(this.toolTypeId);

        if(tool==null) {
            return "";
        }

        return tool.getImagePng();
    }

    /*
    React to Tool Factory
    */
    @Override
    protected String reactToAbyssOrTool(List<Programmer> programmers, Programmer currProgrammer, int boardSize)
    {
        ToolSingletonFactory toolFactory = ToolSingletonFactory.getInstance();
        Tool tool = toolFactory.getTool(this.toolTypeId);

        if(tool==null) {
            return null;
        }

        return tool.reactToAbyssOrTool(programmers, currProgrammer, boardSize);
    }
}
