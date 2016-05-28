
package prog2_a3.fatsquirrel.botapi;

import prog2_a3.fatsquirrel.core.XY;

public interface MiniBotControllerContext extends ControllerContext{
    
    void implode (int radius);
    
    XY getDirectionToParent();
    
}
