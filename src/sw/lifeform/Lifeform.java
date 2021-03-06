package sw.lifeform;

import java.util.Hashtable;
import java.util.Vector;

import sw.combat.Effect;
import sw.environment.Room;
import sw.time.TimeObserver;

/**
 * An abstract class that all life forms in the game are built off of.
 * 
 * TODO: When a new Lifeform is added to the "world" it needs to be setup with all the proper Listeners.  This should be done in one central location.
 * @author cdgira
 *
 */
public abstract class Lifeform implements TimeObserver
{
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String DESC = "DESC";
    public static final String MAX_LIFE = "MAX_LIFE";
    public static final String CURRENT_LIFE = "CURRENT_LIFE";
    public static final String CURRENT_ROOM = "CURRENT_ROOM";
    
    /**
     * The unique id for this lifeform.
     */
    protected int m_id;

    /**
     * The name of the life form.
     * TODO: This is NOT allowed to have spaces.
     * TODO: Do we need a detailed name that would add more for things like NPCs with two names etc...how to deal with that?
     */
    protected String m_name;

    /**
     * The description of the life form.
     */
    protected String m_description;

    /**
     * Maximum life points the life form can have at anyone time.
     */
    protected int m_maxLifePoints;

    /**
     * The Lifeforms's current life points.
     */
    protected int m_currentLifePoints;

    /**
     * The Lifeform's list of current effects.
     */
    protected Vector<Effect> m_effects = new Vector<Effect>();

    /**
     * The room I am current in.
     */
    protected Room m_currentRoom = null;
    
    public Lifeform(String name, String desc, int maxLife)
    {
        this(-1,name,desc,maxLife);
    }

    public Lifeform(int id, String name, String desc, int maxLife)
    {
        m_id = id;
        m_name = name;
        m_description = desc;
        m_maxLifePoints = maxLife;
        m_currentLifePoints = maxLife;
    }

    /**
     * Return the name of the life form.
     * @return
     */
    public String getName()
    {
        return m_name;
    }

    /**
     * Returns the description of the life form.
     * @return
     */
    public String getDescription()
    {
        return m_description;
    }

    /**
     * 
     * @return The max life points for this lifeform.
     */
    public int getMaxLifePoints()
    {
        return m_maxLifePoints;
    }

    /**
     * 
     * @return The current amount of life points this lifeform has.
     */
    public int getCurrentLifePoints()
    {
        return m_currentLifePoints;
    }

    /**
     * To be implemented by concrete lifeforms depending on how they
     * do damage.  The implemented form should use takeHit to inform
     * the entity being attack how much damage was done.
     * @param entity
     */
    public abstract void attack(Lifeform entity);

    /**
     * Informs the lifeform how much damage was done to it and to
     * adjust it's current life points accordingly.
     * @param damage
     */
    public abstract void takeHit(int damage);

    /**
     * Informs the lifeform how much damage was healed to it and to
     * adjsut it's current life points accordingly.
     * @param magnitude
     */
    public abstract void takeHeal(int magnitude);

    /**
     * 
     * @return the unique identifier for this lifeform.
     */
    public int getID()
    {
        return m_id;
    }

    public void addEffect(Effect e)
    {
        m_effects.add(e);
    }

    /**
     * Remove the provided effect form the list of effects.
     */
    public void removeEffect(Effect e)
    {
        m_effects.remove(e);
    }

    public Effect getEffect(int i)
    {
        return m_effects.get(i);
    }

    public int getNumEffects()
    {
        return m_effects.size();
    }

    /**
     * Which room the life form is present in.
     * @return
     */
    public Room getCurrentRoom()
    {
        return m_currentRoom;
    }

    /**
     * Sets the room the lifeform is currently located in.
     * @param room
     */
    public void setCurrentRoom(Room room)
    {
        m_currentRoom = room;
    }
    
    /**
     * Sets the id that uniquely identifies the lifeform.
     * @param id
     */
    public void setID(int id)
    {
        m_id = id;
    }
    
    /**
     * 
     */
    public Hashtable<String,Object> getLifeformInfo()
    {
        Hashtable<String,Object> data = new Hashtable<String,Object>();
        
        data.put(ID, m_id);
        data.put(CURRENT_LIFE, this.m_currentLifePoints);
        data.put(MAX_LIFE, this.m_maxLifePoints);
        if (m_currentRoom != null)
            data.put(CURRENT_ROOM, m_currentRoom.getID());
        else
            data.put(CURRENT_ROOM, -1);
        data.put(DESC, m_description);
        data.put(NAME, m_name);
        
        return data;
    }

    /**
     * Sets the current life points of the lifeform.
     * @param i
     */
    public void setCurrentLifePoints(int points)
    {
        if (points <= m_maxLifePoints)
            m_currentLifePoints = points;
        else
            m_currentLifePoints = m_maxLifePoints;
        
    }

}
