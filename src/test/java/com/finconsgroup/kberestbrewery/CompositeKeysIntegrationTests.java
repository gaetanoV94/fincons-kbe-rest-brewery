package com.finconsgroup.kberestbrewery;

import com.finconsgroup.kberestbrewery.domain.BeerOrderLine;
import com.finconsgroup.kberestbrewery.domain.BeerOrderLineKey;
import com.finconsgroup.kberestbrewery.repositories.BeerOrderLineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CompositeKeysIntegrationTests {

    public static final long BEER_ORDER_ID = 1l;
    public static final long BEER_ID = 2l;

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    BeerOrderLineRepository beerOrderLineRepository;

    @Test
    public void persistBeerOrderLineWithCompositeKeyThenRetrieveDetailsTest() {
        BeerOrderLine beerOrderLineToSave = createBeerOrderLine();
        persist(beerOrderLineToSave);
        BeerOrderLine savedBeerOrderLine = findBeerOrderLineById();
        verifyAssertionsWith(savedBeerOrderLine);
    }

    private void verifyAssertionsWith(BeerOrderLine savedBeerOrderLine) {
        assertNotNull(savedBeerOrderLine);
        assertNotNull(savedBeerOrderLine.getBeerOrderLineKey());
        assertEquals(BEER_ID, savedBeerOrderLine.getBeerOrderLineKey().getBeerId());
        assertEquals(BEER_ORDER_ID, savedBeerOrderLine.getBeerOrderLineKey().getBeerOrderId());
    }

    private BeerOrderLine findBeerOrderLineById() {
        return testEntityManager.find(BeerOrderLine.class, new BeerOrderLineKey(BEER_ORDER_ID, BEER_ID));
    }

    private void persist(BeerOrderLine beerOrderLine) {
        beerOrderLineRepository.save(beerOrderLine);
    }

    private BeerOrderLine createBeerOrderLine() {
        BeerOrderLineKey beerOrderLineKey = new BeerOrderLineKey(BEER_ORDER_ID, BEER_ID);
        BeerOrderLine beerOrderLine = new BeerOrderLine(beerOrderLineKey);
        beerOrderLine.setOrderQuantity(50);
        beerOrderLine.setQuantityAllocated(23);
        return beerOrderLine;
    }

}
