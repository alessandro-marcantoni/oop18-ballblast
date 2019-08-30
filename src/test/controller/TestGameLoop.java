package test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ballblast.controller.Controller;
import ballblast.controller.GameLoopImpl;
import ballblast.model.Model;
import ballblast.model.ModelImpl;
import ballblast.view.View;
import ballblast.view.utilities.ViewScenes;

/**
 * JUnit test for {@Link GameLoop}.
 */
public class TestGameLoop {

    private Model testModel = new ModelImpl();
    private TestView testView = new TestView();
    private final GameLoopImpl gameLoop = new GameLoopImpl(testModel, testView);

    private class TestView implements View {

        private int counter = 0;

        @Override
        public void launch(final Controller controller) {
        }

        @Override
        public void render() {
            this.counter++;
        }

        private int getCounter() {
            return this.counter;
        }

        @Override
        public void loadScene(final ViewScenes scene) {
        }

    }

    private void waitOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the gameLoop.
     */
    @Before
    public void initializeEnv() {
        this.testModel.startSurvival();
        this.gameLoop.start();
    }

    /**
     * Tests {@Link GameLoop} start.
     */
    @Test
    public void testStart() {
        assertTrue(this.gameLoop.isAlive());
    }

    /**
     * Tests {@Link GameLoop} update.
     */
    @Test
    public void testLoop() {
        final int previousCounterValue = this.testView.getCounter();
        this.waitOneSecond();
        assertTrue(this.testView.getCounter() > previousCounterValue);
    }

    /**
     * Tests {@Link GameLoop} pause feature.
     */
    @Test
    public void testPause() {
        this.waitOneSecond();
        final int previousValue = this.testView.getCounter();
        this.gameLoop.pause();
        this.waitOneSecond();
        assertEquals(previousValue, this.testView.getCounter());
    }

    /**
     * Tests {@Link GameLoop} resume feature.
     */
    @Test
    public void testResume() {
        this.waitOneSecond();
        final int previousValue = this.testView.getCounter();
        this.gameLoop.pause();
        this.gameLoop.resumeLoop(); 
        this.waitOneSecond();
        assertTrue(previousValue < this.testView.getCounter());
    }

    /**
     * 
     */
    @Test
    public void testFrameRate() {
        int previousValue = this.testView.getCounter();
        for (int i = 0; i < 10; i++) {
            this.waitOneSecond();
            int newValue = this.testView.getCounter();
            System.out.println(newValue - previousValue);
            previousValue = newValue;
        }
    }

}
