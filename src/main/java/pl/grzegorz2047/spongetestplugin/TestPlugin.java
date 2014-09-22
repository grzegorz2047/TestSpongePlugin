package pl.grzegorz2047.spongetestplugin;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.BaseEvent;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.SpongeEventHandler;
import org.spongepowered.api.event.state.PreInitializationEvent;
import org.spongepowered.api.event.state.ServerStartingEvent;
import org.spongepowered.api.event.voxel.VoxelEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "TestPlugin", name = "TestPlugin")
public class TestPlugin {
    Game game;
    
    @SpongeEventHandler
    public void onConstruction(PreInitializationEvent event) {
        game = event.getGame();
        game.broadcastMessage("Ten event wykonuje sie przy pre inicjacji");
    }

    @SpongeEventHandler
    public void onServerStarting(ServerStartingEvent event) {
        game.broadcastMessage("Tu wykonuje server starting event!");
        
        game.broadcastMessage("Tu jakis swoj event sobie wywoluje");
        Event e = new MyEvent("Tu jest przykladowa wiadomosci w moim evencie");
        game.getEventManager().call(e);
    }
    
    @SpongeEventHandler
    public void onMyEvent(MyEvent event) {
        game.broadcastMessage("Wykonano moj stworzony event (" + event.getMessage() + ")");
    }
    
    @SpongeEventHandler
    public void onVoxelEvent(VoxelEvent event) {
        String id = event.getVoxel().getBlock().getID();
        game.broadcastMessage("Zniszczono blok o id "+id);
        try{
            if(game.getOnlinePlayers().size()>0){
               game.broadcastMessage("Blok zostal zniszczony");
               
            }
        }
        catch(Exception ex){
            
        }
    }
    
    public class MyEvent extends BaseEvent {
        
        private final String message;
        
        public MyEvent(String message) {
            this.message = message;
        }
        
        public String getMessage() {
            return message;
        }
        
    }
}