package cn.hellp.touch.unitem.selector.tools.player;

import cn.hellp.touch.unitem.auxiliary.Getter;
import cn.hellp.touch.unitem.auxiliary.Number;
import cn.hellp.touch.unitem.selector.ISelector;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerGetter extends Getter<Player> {
    private final ISelector<?> target;

    public PlayerGetter(ISelector<?> target) {
        this.target = target;
    }

    public PlayerGetter() {
        this(null);
    }

    protected abstract Object get(Player invoker);

    @Override
    public Object[] select(Player invoker) {
        Object o;
        if(target==null) {
            o= get(invoker);
            if(o==null) {
                return new Object[0];
            }
            if(o instanceof Number) {
                o = new Number(o);
            }
            return new Object[] {o};
        } else {
            List<Object> result = new ArrayList<>();
            for (Object player : target.select(invoker)) {
                o= get(((Player) player));
                if(o instanceof Number) {
                    o = new Number(o);
                }
                if(o!=null) {
                    result.add(o);
                }
            }
            return result.toArray(new Object[0]);
        }
    }
}
