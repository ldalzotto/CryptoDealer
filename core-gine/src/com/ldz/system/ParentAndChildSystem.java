package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 * <p>
 * This {@link System} allow linking parent and childs of all entities. Only entities possessing {@link ParentAndChildComponent} are tagged for child and parent linking.
 * The processing of entites is executed only after the completion of adding all entities contained in bag of entites in engine {@link BagOfEntitiesToEngineSystem#allBagsDisplayed()}.
 * </p>
 */
public class ParentAndChildSystem extends EntitySystem implements IRetrieveAllEntitiesFromSystem {

    private static final String TAG = ParentAndChildSystem.class.getSimpleName();

    private static ParentAndChildSystem instance = null;
    private ImmutableArray<Entity> entityList;

    public static ParentAndChildSystem getInstance() {
        if (instance == null) {
            instance = new ParentAndChildSystem();
        }
        return instance;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.entityList = engine.getEntitiesFor(Family.all(ParentAndChildComponent.class, BagOfEntitiesComponent.class)
                .get());
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    /**
     * <p>
     * The update method is :
     * <ul><li>
     * Apply the parent and child linking based on the tree {@code childEntities} on entites contained in {@link ParentAndChildSystem}
     * </li></ul>
     * </p>
     *
     * @param deltaTime the delta time of Engine update
     */
    @Override
    public void update(float deltaTime) {
        if (BagOfEntitiesToEngineSystem.getInstance().allBagsDisplayed()) {

            super.update(deltaTime);

            Gdx.app.debug(TAG, "Starting setting parent and child of : " + ReflectionToStringBuilder.toString(this.entityList));
            linkParentAndChildRecursively(Arrays.asList(this.entityList.toArray()));
            Gdx.app.debug(TAG, "Setting parent and child ending successfully.");

            this.setProcessing(false);
        }
    }

    /**
     * <p>Applique de manière récursive le lien parent/enfant entre les différentes entitées contenues dans {@code entitiesToBind}.
     * Un lien parent/enfant est créé lorsque une composante {@link BagOfEntitiesComponent} est présente dans l'entité parent.
     * Cette composant permet d'identifier les entitées à lier.</p>
     * <p>Cette méthode est récursive, si une entitée présente dans un {@link BagOfEntitiesComponent} d'une des entitées de
     * {@code entitiesToBind} possède également son propre {@link BagOfEntitiesComponent}, alors cette méthode est re-appliquée
     * pour les dernières entitées trouvées.</p>
     *
     * @param entitiesToBind la liste d'entité sur laquelle nous souhaitons appliquer les liens parent/enfant.
     */

    private void linkParentAndChildRecursively(List<Entity> entitiesToBind) {
        for (Entity entity :
                entitiesToBind) {
            //get component
            BagOfEntitiesComponent bagOfEntitiesComponent = entity.getComponent(BagOfEntitiesComponent.class);
            if (bagOfEntitiesComponent != null) {
                applyLinking(entity, bagOfEntitiesComponent.entities);
                linkParentAndChildRecursively(bagOfEntitiesComponent.entities);
            }
        }
    }

    /**
     * <p>Effectue un lien parent/enfant entre l'entité parent {@code parentEntity} et les entitées enfant {@code childEntities}.</p>
     * <p>Ce lien est porté par la composante {@link ParentAndChildComponent} de toutes les entités.</p>
     * <p>Le lien est bi-directionel.</p>
     *
     * @param parentEntity  l'entité parent
     * @param childEntities les entitées enfant.
     */
    private void applyLinking(Entity parentEntity, List<Entity> childEntities) {
        ParentAndChildComponent parentAndChildComponent = parentEntity.getComponent(ParentAndChildComponent.class);
        if (parentAndChildComponent != null) {
            parentAndChildComponent.childs.clear();
            parentAndChildComponent.childs.addAll(childEntities);
        }

        //set parents
        for (Entity entityChild :
                childEntities) {
            ParentAndChildComponent parentAndChildComponentChild = entityChild.getComponent(ParentAndChildComponent.class);
            parentAndChildComponentChild.parent = parentEntity;
        }

    }

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(this.entityList);
        return entities;
    }
}
