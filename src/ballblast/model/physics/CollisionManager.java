package ballblast.model.physics;

import org.locationtech.jts.geom.Envelope;

import com.google.common.collect.ImmutableList;

/**
 * The manager to check the collision during the game session.
 */
public class CollisionManager {

    private ImmutableList<Collidable> collidables;
    /**
     * The costructor to initialize the list of collidable objects.
     */
    public CollisionManager() {
        this.collidables = ImmutableList.of();
    }

    /**
     * The main method to check if there are collision between game objects.
     */
    public final void checkLoop() {

        for (int i = 0; i < this.collidables.size(); i++) {
            for (int j = i + 1; j < this.collidables.size(); j++) {
                final Collidable c1 = this.collidables.get(i);
                final Collidable c2 = this.collidables.get(j);
                checkCollision(c1, c2);
            }
        }
    }

    private void checkCollision(final Collidable c1, final Collidable c2) {

        final CollisionTag t1 = c1.getCollisionTag();
        final CollisionTag t2 = c2.getCollisionTag();

        if (t1.canCollideWith(t2)) {
            final Envelope s1 = c1.generateShape();
            final Envelope s2 = c2.generateShape();
            if (s1.intersects(s2)) {
                c1.notifyCollision(new Collision(c1, c2));
                //c2.notifyCollision(new Collision(c2.getCollisionTag(), c1));
            }
        }
    }

    /**
     * Add a new {@link Collidable} object to the list.
     * @param coll
     *       the {@link Collidable} object to add in the list.
     */
    public final void addCollidable(final Collidable coll) {
        if (this.collidables.isEmpty()) {
            this.collidables = ImmutableList.of(coll);
        } else {
            this.collidables = ImmutableList.<Collidable>builder()
                               .addAll(this.collidables)
                               .add(coll)
                               .build();
        }
    }

    /**
     * Remove a {@link Collidable} object from the list.
     * @param coll
     *       the {@link Collidable} object to remove form the list.
     */
    public final void removeCollidable(final Collidable coll) {
        this.collidables = this.collidables.stream()
                                           .filter(c -> !c.equals(coll))
                                           .collect(ImmutableList.toImmutableList());
    }

}
