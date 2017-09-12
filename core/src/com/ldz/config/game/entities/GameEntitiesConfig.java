package com.ldz.config.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ldz.config.game.entities.domain.Constructor;
import com.ldz.config.game.entities.domain.GameEntities;
import com.ldz.config.game.entities.domain.GameEntity;
import com.ldz.config.game.entities.domain.Parameter;
import com.ldz.config.game.entities.instance.domain.GameEntitiesInstance;
import com.ldz.config.game.entities.instance.domain.GameEntityInstance;
import com.ldz.config.itf.IAddingInstanceChildOnComplete;
import com.ldz.entity.EntityWithId;
import com.ldz.util.LoggingUtil;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class GameEntitiesConfig {

    private static final String TAG = GameEntitiesConfig.class.getSimpleName();

    private static GameEntitiesConfig instance = null;
    private Json json = new Json();
    private GameEntities gameEntities = new GameEntities();
    private GameEntitiesInstance gameEntitiesInstance = new GameEntitiesInstance();

    private GameEntitiesConfig() {
        this.gameEntities.setEntities(new ArrayList<>());
        List<GameEntities> gameEntities = new ArrayList<>();

        gameEntities.add(json.fromJson(GameEntities.class, Gdx.files.internal("config/entity/GameEntities.json")));
        gameEntities.add(json.fromJson(GameEntities.class, Gdx.files.internal("config/entity/DelayedDisplayerEntity.json")));
        gameEntities.add(json.fromJson(GameEntities.class, Gdx.files.internal("config/entity/ExitBoxEntity.json")));
        gameEntities.add(json.fromJson(GameEntities.class, Gdx.files.internal("config/entity/PopUpEntity.json")));
        gameEntities.add(json.fromJson(GameEntities.class, Gdx.files.internal("config/entity/UpgradeObjectsEntity.json")));
        gameEntities.add(json.fromJson(GameEntities.class, Gdx.files.internal("config/entity/PersistantUpgrade.json")));
        gameEntities.add(json.fromJson(GameEntities.class, Gdx.files.internal("config/entity/CriticalStateDisplayEntity.json")));

        for (GameEntities gameEntities1 :
                gameEntities) {
            this.gameEntities.getEntities().addAll(gameEntities1.getEntities());
        }


        this.gameEntitiesInstance.setEntities(new ArrayList<>());
        List<GameEntitiesInstance> gameEntitiesInstances = new ArrayList<>();

        gameEntitiesInstances.add(json.fromJson(GameEntitiesInstance.class, Gdx.files.internal("config/entityinstance/GameEntityInstance.json")));
        gameEntitiesInstances.add(json.fromJson(GameEntitiesInstance.class, Gdx.files.internal("config/entityinstance/ExitBoxEntityInstace.json")));
        gameEntitiesInstances.add(json.fromJson(GameEntitiesInstance.class, Gdx.files.internal("config/entityinstance/UpgradeObjectEntityInstance.json")));
        gameEntitiesInstances.add(json.fromJson(GameEntitiesInstance.class, Gdx.files.internal("config/entityinstance/PopupEntityInstance.json")));
        gameEntitiesInstances.add(json.fromJson(GameEntitiesInstance.class, Gdx.files.internal("config/entityinstance/PersistantUpgradeInstance.json")));
        gameEntitiesInstances.add(json.fromJson(GameEntitiesInstance.class, Gdx.files.internal("config/entityinstance/CriticalStateDisplayEntityInstance.json")));


        for (GameEntitiesInstance gameEntitiesInstance :
                gameEntitiesInstances) {
            this.gameEntitiesInstance.getEntities().addAll(gameEntitiesInstance.getEntities());
        }

    }

    public static GameEntitiesConfig getInstance() {
        if (instance == null) {
            instance = new GameEntitiesConfig();
        }
        return instance;
    }

    public EntityWithId buildEntityFromEntityIdAndArguments(EntityId entityId, Object... args) {

        String classNameToinstantiate = StringUtils.EMPTY;
        for (GameEntity gameEntity :
                this.getGameEntities().getEntities()) {
            if (entityId.equals(gameEntity.getId())) {
                classNameToinstantiate = gameEntity.getInstance().getConstructor().getClassname();
            }
        }

        EntityWithId entityToReturn = null;

        try {
            if (!classNameToinstantiate.equals(StringUtils.EMPTY)) {

                List<Class> constructorClassParam = new ArrayList<>();
                for (Object arg :
                        args) {
                    constructorClassParam.add(this.convertWrapperClassToPrimitive(arg.getClass()));
                }

                java.lang.reflect.Constructor constructor = this.getJavaConstructorFromClass(Class.forName(classNameToinstantiate),
                        constructorClassParam.toArray(new Class[]{}), new ArrayList<>(constructorClassParam));
                entityToReturn = (EntityWithId) constructor.newInstance(args);
                entityToReturn.setId(entityId);
            } else {
                throw new RuntimeException("Cannot instante the entity with id " + ReflectionToStringBuilder.toString(entityId) + " with arguments : + "
                        + ReflectionToStringBuilder.toString(args));
            }
        } catch (Exception e) {
            LoggingUtil.DEBUG(TAG, e.getMessage(), e);
        }

        return entityToReturn;
    }

    public GameEntities getGameEntities() {
        return gameEntities;
    }

    public EntityWithId buildEntityByInstanceEntityid(InstanceEntityId instanceEntityId) {

        try {
            //get entity
            for (GameEntityInstance gameEntityInstance :
                    this.gameEntitiesInstance.getEntities()) {

                if (gameEntityInstance.getInstanceId().equals(instanceEntityId)) {
                    List<Object> constructedConstructorArgs = new ArrayList<>();
                    for (Parameter parameter :
                            gameEntityInstance.getConstructorArgs()) {

                        constructedConstructorArgs
                                .add(this.buildParemeter(new ArrayList<>(), new ArrayList<>(), parameter));

                    }

                    //call constructor of entityId
                    EntityId entityId = gameEntityInstance.getEntityId();
                    GameEntity gameEntityToInstantiate = this.findGameEntityFromEntityId(entityId);
                    Constructor constructor = gameEntityToInstantiate.getInstance().getConstructor();

                    EntityWithId entityWithId = (EntityWithId) this.buildInstance(constructor, constructedConstructorArgs.toArray());
                    entityWithId.setId(entityId);
                    entityWithId.setIstanceId(instanceEntityId);


                    if (gameEntityInstance.getAddChildOnComplete() != null) {
                        Constructor addingChildConstructor = gameEntityInstance.getAddChildOnComplete().getConstructor();
                        IAddingInstanceChildOnComplete iAddingInstanceChildOnComplete = (IAddingInstanceChildOnComplete) buildInstance(addingChildConstructor);
                        iAddingInstanceChildOnComplete.addingChilds(entityWithId);
                    }

                    return entityWithId;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Deprecated
    public EntityWithId buildEntityById(EntityId id) {

        //get entity
        try {
            for (GameEntity gameEntity :
                    this.gameEntities.getEntities()) {
                if (gameEntity.getId().equals(id)) {
                    //get constructor
                    Constructor jsonConstructor = gameEntity.getInstance().getConstructor();

                    EntityWithId entityWithId = (EntityWithId) buildInstance(jsonConstructor);
                    entityWithId.setId(id);

                    if (gameEntity.getAddChildOnComplete() != null) {
                        Constructor addingChildConstructor = gameEntity.getAddChildOnComplete().getConstructor();
                        IAddingInstanceChildOnComplete iAddingInstanceChildOnComplete = (IAddingInstanceChildOnComplete) buildInstance(addingChildConstructor);
                        iAddingInstanceChildOnComplete.addingChilds(entityWithId);
                    }

                    return entityWithId;

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    private Object buildInstance(Constructor jsonConstructor) throws Exception {
        Class currentClass = Class.forName(jsonConstructor.getClassname());

        List<Class> types = new ArrayList<>();
        List<Object> parametersValues = new ArrayList<>();

        for (Parameter parameter :
                jsonConstructor.getParameters()) {
            buildParemeter(types, parametersValues, parameter);
        }

        java.lang.reflect.Constructor objectConstructor = getJavaConstructorFromClass(currentClass, types.toArray(new Class[]{}),
                new ArrayList<>(types));
        return objectConstructor.newInstance((Object[]) parametersValues.toArray());
    }

    private Object buildInstance(Constructor jsonConstructor, Object... constructorArgs) throws Exception {
        Class currentClass = Class.forName(jsonConstructor.getClassname());
        List<Class> types = new ArrayList<>();


        for (Parameter parameter :
                jsonConstructor.getParameters()) {
            types.add(this.determineClassWithLittleType(parameter.getClassname()));
        }

        java.lang.reflect.Constructor objectConstructor = getJavaConstructorFromClass(currentClass, types.toArray(new Class[]{}),
                new ArrayList<>(types));
        return objectConstructor.newInstance(constructorArgs);
    }

    private Object buildParemeter(List<Class> types, List<Object> parametersValues, Parameter parameter) throws Exception {

        if (parameter.getConstructor() == null) {
            Object o;
            if (parameter.getClassname().contains("Integer")) {
                o = Integer.valueOf(parameter.getValue());
                types.add(Integer.class);
            } else if (parameter.getClassname().contains("int")) {
                if (parameter.getValue().contains("0x")) {
                    o = (int) Long.parseLong(parameter.getValue().replace("0x", ""), 16);
                    types.add(int.class);
                } else {
                    o = Integer.valueOf(parameter.getValue());
                    types.add(int.class);
                }
            } else if (parameter.getClassname().contains("Float")) {
                o = Float.valueOf(parameter.getValue());
                types.add(Float.class);
            } else if (parameter.getClassname().contains("float")) {
                o = Float.valueOf(parameter.getValue());
                types.add(float.class);
            } else {
                Class currentClass = Class.forName(parameter.getClassname());
                o = parameter.getValue();
                types.add(currentClass);
            }
            parametersValues.add(o);
            return o;
        } else {
            if (parameter.getClassname().contains("List|")) {

                List arrayList = new ArrayList();

                //get constructor
                Constructor constructor = parameter.getConstructor();

                for (Parameter arrayParameter :
                        constructor.getParameters()) {
                    arrayList.add(buildParemeter(new ArrayList<>(), new ArrayList<>(), arrayParameter));
                }

                types.add(ArrayList.class);
                parametersValues.add(arrayList);
                return arrayList;


            } else {
                Object o = buildInstance(parameter.getConstructor());
                types.add(o.getClass());
                parametersValues.add(o);
                return o;
            }

        }

    }

    private java.lang.reflect.Constructor getJavaConstructorFromClass(Class classWhereConstructorIs, Class[] constructorParameters,
                                                                      List<Class> initialConstructorParameters) {

        List<Exception> exceptionsOccured = new ArrayList<>();

        java.lang.reflect.Constructor constructorReturn = null;

        try {
            constructorReturn = classWhereConstructorIs.getConstructor(constructorParameters);
        } catch (NoSuchMethodException e) {

            //try with interface ?
            int contructorParameterIndex = 0;

            constructorParametersLoop:
            for (Class aClass :
                    constructorParameters) {
                List<Class<?>> interfaces = ClassUtils.getAllInterfaces(aClass);
                for (Class interfaceClass :
                        interfaces) {
                    Class[] constructorParametersClone = new ArrayList<>(initialConstructorParameters).toArray(new Class[]{});
                    constructorParametersClone[contructorParameterIndex] = interfaceClass;

                    try {
                        constructorReturn = classWhereConstructorIs.getConstructor(constructorParametersClone);
                    } catch (NoSuchMethodException e1) {
                        exceptionsOccured.add(e1);
                    }

                    if (constructorReturn != null) {
                        break constructorParametersLoop;
                    }
                }
                contructorParameterIndex += 1;
            }

            exceptionsOccured.add(e);
        }


        //error log if occur
        if (constructorReturn == null && exceptionsOccured.size() > 0) {
            for (Exception e :
                    exceptionsOccured) {
                Gdx.app.debug(TAG, e.getMessage() + " " + e.getStackTrace());
            }
        }

        return constructorReturn;
    }

    private GameEntity findGameEntityFromEntityId(EntityId entityId) {
        for (GameEntity gameEntity :
                this.gameEntities.getEntities()) {
            if (gameEntity.getId().equals(entityId)) {
                return gameEntity;
            }
        }
        return null;
    }

    private Class determineClassWithLittleType(String className) throws Exception {

        if (className.contains("Integer")) {
            return Integer.class;
        } else if (className.contains("int")) {
            return int.class;
        } else if (className.contains("Float")) {
            return Float.class;
        } else if (className.contains("float")) {
            return float.class;
        } else if (className.contains("List|")) {
            return ArrayList.class;
        } else {
            return Class.forName(className);
        }
    }

    private Class convertWrapperClassToPrimitive(Class aClass) {

        if (aClass.getName().contains("Integer")) {
            return int.class;
        } else if (aClass.getName().contains("Float")) {
            return float.class;
        } else if (aClass.getName().contains("Double")) {
            return double.class;
        } else if (aClass.getName().contains("Long")) {
            return long.class;
        } else {
            return aClass;
        }

    }

}
