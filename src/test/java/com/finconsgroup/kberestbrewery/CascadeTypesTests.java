package com.finconsgroup.kberestbrewery;


import com.finconsgroup.kberestbrewery.domain.Beer;
import com.finconsgroup.kberestbrewery.domain.BeerInventory;
import org.hibernate.*;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CascadeTypesTests {

    public static final long BEER_ORDER_ID = 1l;
    public static final long BEER_ID = 1l;

    private static SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @BeforeAll
    public static void beforeTests() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @BeforeEach
    public void setUp() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    public void tearDown() {
        transaction.rollback();
        session.close();
    }

    /* The persist operation makes a transient instance persistent. Cascade Type PERSIST propagates
     * the persist operation from a parent to a child entity. When we save the beer entity, the beerInventory entity will also get saved.
     *
     * insert into Beer (id, createdDate, lastModifiedDate, version, beerName, beerStyle, minOnHand, price, quantityToBrew, upc) values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)
     * insert into BeerInventory (id, createdDate, lastModifiedDate, version, beer_id, quantityOnHand) values (null, ?, ?, ?, ?, ?)
     */
    @Test
    public void whenParentSavedThenChildSavedTest() {
        Beer beer = new Beer();
        BeerInventory beerInventory = new BeerInventory();
        beerInventory.setBeer(beer);
        beer.setBeerInventory(Set.of(beerInventory));
        session.persist(beer);
        session.flush();
        session.clear();
    }

    /* The merge operation copies the state of the given object onto the persistent object with the same identifier.
     * CascadeType.MERGE propagates the merge operation from a parent to a child entity.
     *
     * select beerinvent0_.id as id1_1_0_, beerinvent0_.createdDate as createdd2_1_0_, beerinvent0_.lastModifiedDate as lastmodi3_1_0_, beerinvent0_.version as version4_1_0_, beerinvent0_.beer_id as beer_id6_1_0_, beerinvent0_.quantityOnHand as quantity5_1_0_, beer1_.id as id1_0_1_, beer1_.createdDate as createdd2_0_1_, beer1_.lastModifiedDate as lastmodi3_0_1_, beer1_.version as version4_0_1_, beer1_.beerName as beername5_0_1_, beer1_.beerStyle as beerstyl6_0_1_, beer1_.minOnHand as minonhan7_0_1_, beer1_.price as price8_0_1_, beer1_.quantityToBrew as quantity9_0_1_, beer1_.upc as upc10_0_1_ from BeerInventory beerinvent0_ left outer join Beer beer1_ on beerinvent0_.beer_id=beer1_.id where beerinvent0_.id=?
     * update BeerInventory set lastModifiedDate=?, version=?, beer_id=?, quantityOnHand=? where id=? and version=?
     * update Beer set lastModifiedDate=?, version=?, beerName=?, beerStyle=?, minOnHand=?, price=?, quantityToBrew=?, upc=? where id=? and version=?
     */
    @Test
    public void whenParentSavedThenMergedTest() {
        long beerInventoryId;
        Beer beer = createBeer("Ice Blonde");
        BeerInventory beerInventory = createBeerInventory(beer);
        beer.setBeerInventory(Set.of(beerInventory));
        session.persist(beer);
        session.flush();
        beerInventoryId = beerInventory.getId();
        session.clear();

        BeerInventory savedBeerInventory = session.find(BeerInventory.class, beerInventoryId);
        Beer savedBeer = savedBeerInventory.getBeer();
        savedBeer.setBeerName("Red Fisher");
        savedBeerInventory.setQuantityOnHand(24);
        session.merge(savedBeer);
        session.flush();
    }

    /* CascadeType.REMOVE propagates the remove operation from parent to child entity. Similar to JPA's CascadeType.REMOVE,
     * we have CascadeType.DELETE, which is specific to Hibernate.
     *
     * delete from BeerInventory where id=? and version=?
     * delete from Beer where id=? and version=?
     */
    @Test
    public void whenParentRemovedThenChildRemovedTest() {
        long beerId;
        Beer beer = createBeer("Ice Blonde");
        BeerInventory beerInventory = createBeerInventory(beer);
        beer.setBeerInventory(Set.of(beerInventory));
        session.persist(beer);
        session.flush();
        beerId = beer.getId();
        session.clear();

        Beer savedBeer = session.find(Beer.class, beerId);
        session.remove(savedBeer);
        session.flush();
    }

    /* The detach operation removes the entity from the persistent context. When we use CascadeType.DETACH,
     * the child entity will also get removed from the persistent context.
     */
    @Test
    public void whenParentDetachedThenChildDetached() {
        Beer beer = createBeer("Ice Blonde");
        BeerInventory beerInventory = createBeerInventory(beer);
        beer.setBeerInventory(Set.of(beerInventory));
        session.persist(beer);
        session.flush();

        Assertions.assertTrue(session.contains(beer));
        Assertions.assertTrue(session.contains(beerInventory));

        session.detach(beer);
        Assertions.assertFalse(session.contains(beer));
        Assertions.assertFalse(session.contains(beerInventory));
    }

    /* Unintuitively, CascadeType.LOCK reattaches the entity and its associated child entity
     * with the persistent context again.
     */
    @Test
    public void whenDetachedAndLockedThenBothReattachedTest() {
        Beer beer = createBeer("Ice Blonde");
        BeerInventory beerInventory = createBeerInventory(beer);
        beer.setBeerInventory(Set.of(beerInventory));
        session.persist(beer);
        session.flush();

        Assertions.assertTrue(session.contains(beer));
        Assertions.assertTrue(session.contains(beerInventory));

        session.detach(beer);
        Assertions.assertFalse(session.contains(beer));
        Assertions.assertFalse(session.contains(beerInventory));
        session.unwrap(Session.class)
                .buildLockRequest(new LockOptions(LockMode.NONE))
                .lock(beer);

        Assertions.assertTrue(session.contains(beer));
        Assertions.assertTrue(session.contains(beerInventory));
    }

    /* Refresh operations reread the value of a given instance from the database. In some cases, we may change an instance after persisting in the database,
     * but later we need to undo those changes. In that kind of scenario, this may be useful. When we use this operation with Cascade Type REFRESH,
     * the child entity also gets reloaded from the database whenever the parent entity is refreshed.
     */
    @Test
    public void whenParentRefreshedThenChildRefreshedTest() {
        Beer beer = createBeer("Ice Blonde");
        BeerInventory beerInventory = createBeerInventory(beer);
        beer.setBeerInventory(Set.of(beerInventory));
        session.persist(beer);
        session.flush();
        beer.setBeerName("Red Cat");
        beerInventory.setQuantityOnHand(12);
        session.refresh(beer);

        Assertions.assertEquals("Ice Blonde", beer.getBeerName());
        Assertions.assertEquals(10, beerInventory.getQuantityOnHand());
    }

    /* The replicate operation is used when we have more than one data source and we want the data in sync. With CascadeType.REPLICATE,
     * a sync operation also propagates to child entities whenever performed on the parent entity.
     *
     * insert into Beer (id, createdDate, lastModifiedDate, version, beerName, beerStyle, minOnHand, price, quantityToBrew, upc) values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)
     * insert into BeerInventory (id, createdDate, lastModifiedDate, version, beer_id, quantityOnHand) values (null, ?, ?, ?, ?, ?)
     * Flushed: 1 (re)creations, 0 updates, 0 removals to 1 collections
     */
    @Test
    public void whenParentReplicatedThenChildReplicatedTest() {
        Beer beer = createBeer("Ice Blonde");
        beer.setId(1l);
        BeerInventory beerInventory = createBeerInventory(beer);
        beerInventory.setId(1l);
        beer.setBeerInventory(Set.of(beerInventory));
        session.unwrap(Session.class).replicate(beer, ReplicationMode.OVERWRITE);
        session.flush();

    }

    /* CascadeType.SAVE_UPDATE propagates the same operation to the associated child entity. It's useful when we use Hibernate-specific operations like save,
     * update and saveOrUpdate.
     *
     * insert into Beer (id, createdDate, lastModifiedDate, version, beerName, beerStyle, minOnHand, price, quantityToBrew, upc) values (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)
     * insert into BeerInventory (id, createdDate, lastModifiedDate, version, beer_id, quantityOnHand) values (null, ?, ?, ?, ?, ?)
     */
    @Test
    public void whenParentSaveOrUpdateThenChildSavedTest() {
        Beer beer = createBeer("Ice Blonde");
        BeerInventory beerInventory = createBeerInventory(beer);
        beer.setBeerInventory(Set.of(beerInventory));
        session.saveOrUpdate(beer);
        session.flush();
    }

    private BeerInventory createBeerInventory(Beer beer) {
        BeerInventory beerInventory = new BeerInventory();
        beerInventory.setQuantityOnHand(10);
        beerInventory.setBeer(beer);
        return beerInventory;
    }

    private Beer createBeer(String name) {
        Beer beer = new Beer();
        beer.setBeerName(name);
        return beer;
    }

}
