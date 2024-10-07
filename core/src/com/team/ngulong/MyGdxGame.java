package com.team.ngulong;

import code.main.GameMidlet;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import lib.MapKey;
import lib.Rms;
import lib.Session_ME;
import lib.mGraphics;
import lib.mSystem;

public class MyGdxGame implements ApplicationListener, InputProcessor {
    private SpriteBatch batch;
    public static GameMidlet gmidlet;
    private InputMultiplexer inputMultiplexer;
    private MyGdxGame.MyInputProcessor inputProcessor;
    private MyGdxGame.MyGestureHandler gestureHandler;
    public float zoom = 1.0F;
    public long timeUpdate;
    public static MyGdxGame me;
    private SpriteBatch batcher;
    private mGraphics g;
    public OrthographicCamera camera;
    public static String mainThreadName;
    public static boolean isStart;
    public static boolean isPause;
    public static boolean isPC = false;
    public static boolean isAndroid;
    public static final byte IPHONE_JB = 2;
    public static final byte IP_APPSTORE = 3;

    public static int getWidth() {
        return Gdx.graphics.getWidth();
    }

    public static int getHeight() {
        return Gdx.graphics.getHeight();
    }

    public void initaliseInputProcessors() {
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(this.inputMultiplexer);
        this.inputProcessor = new MyGdxGame.MyInputProcessor();
        this.gestureHandler = new MyGdxGame.MyGestureHandler();
        this.inputMultiplexer.addProcessor(new GestureDetector(this.gestureHandler));
        this.inputMultiplexer.addProcessor(this.inputProcessor);
    }

    @Override
    public void create() {
        try {
            if (Thread.currentThread().getName() != "Main") {
                Thread.currentThread().setName("Main");
            }

            mainThreadName = Thread.currentThread().getName();
            this.initaliseInputProcessors();
            MyGdxGame.MyInputProcessor inputProcessor = new MyGdxGame.MyInputProcessor();
            Gdx.input.setInputProcessor(inputProcessor);
            MapKey.load();
            this.camera = new OrthographicCamera((float) Gdx.graphics.getWidth(), (float) Gdx.graphics.getHeight());
            this.camera.setToOrtho(true);
            me = this;
            this.g = new mGraphics(new SpriteBatch());
            gmidlet = new GameMidlet();
            mSystem.println("---- " + Gdx.app.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Rms.deleteRecordCompareToName();
    }

    public void render() {
        try {
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
            Gdx.gl.glClear(16384);
            this.g.g.setProjectionMatrix(this.camera.combined);
            this.camera.zoom = this.zoom;
            this.camera.update();
            this.update();
            this.g.begin();
            GameMidlet.gameCanvas.paint(this.g);
            this.g.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update() {
        try {
            Session_ME.update();
            GameMidlet.gameCanvas.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dispose() {
    }

    public boolean keyDown(int keycode) {
        int k = MapKey.map(keycode);
        GameMidlet var10000 = GameMidlet.instance;
        GameMidlet.gameCanvas.keyPressed(keycode);
        return false;
    }

    public boolean keyUp(int keycode) {
        int k = MapKey.map(keycode);
        GameMidlet var10000 = GameMidlet.instance;
        GameMidlet.gameCanvas.keyReleased(k);
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public static void exitApp() {
        Gdx.app.exit();
    }

    class MyGestureHandler implements GestureDetector.GestureListener {
        public float initialScale = 1.0F;

        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        public boolean zoom(float initialDistance, float distance) {
            float var10000 = initialDistance / distance;
            return true;
        }

        public boolean tap(float x, float y, int count, int button) {
            return false;
        }

        public boolean longPress(float x, float y) {
            return false;
        }

        public boolean fling(float velocityX, float velocityY, int button) {
            return false;
        }

        public boolean pan(float x, float y, float deltaX, float deltaY) {
            return false;
        }

        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }

        @Override
        public void pinchStop() {

        }
    }

    class MyInputProcessor implements InputProcessor {
        public boolean scrolled(int amount) {
            if (amount > 0) {
            }

            if (amount < 0) {
            }

            return true;
        }

        public boolean keyDown(int keycode) {
            int k = MapKey.map(keycode);
            if ((Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60)) && keycode == 9) {
                k = 64;
            }

            if (MyGdxGame.gmidlet != null && GameMidlet.gameCanvas != null) {
                GameMidlet.gameCanvas.keyPressed(k);
            }

            return false;
        }

        public boolean keyUp(int keycode) {
            int k = MapKey.map(keycode);
            GameMidlet.gameCanvas.keyReleased(k);
            return false;
        }

        public boolean keyTyped(char character) {
            return false;
        }

        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            int rotation = Gdx.input.getRotation();
            int oy;
            if (rotation == 90) {
                oy = screenX;
                screenX = screenY;
                screenY = Gdx.graphics.getHeight() - oy;
            } else if (rotation == 270) {
                oy = screenY;
                screenY = screenX;
                screenX = Gdx.graphics.getWidth() - oy;
            }

            Vector3 touch = new Vector3((float) screenX, (float) screenY, 0.0F);
            MyGdxGame.this.camera.unproject(touch);
            int delX = (int) touch.x - screenX;
            int delY = (int) touch.y - screenY;
            if (pointer < 2) {
                GameMidlet.gameCanvas.pointerPressed(screenX + delX, screenY + delY, pointer);
            }

            return false;
        }

        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            int rotation = Gdx.input.getRotation();
            int oy;
            if (rotation == 90) {
                oy = screenX;
                screenX = screenY;
                screenY = Gdx.graphics.getHeight() - oy;
            } else if (rotation == 270) {
                oy = screenY;
                screenY = screenX;
                screenX = Gdx.graphics.getWidth() - oy;
            }

            Vector3 touch = new Vector3((float) screenX, (float) screenY, 0.0F);
            MyGdxGame.this.camera.unproject(touch);
            int delX = (int) touch.x - screenX;
            int delY = (int) touch.y - screenY;
            if (pointer < 2) {
                GameMidlet.gameCanvas.pointerReleased(screenX + delX, screenY + delY, pointer);
            }

            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        public boolean touchDragged(int screenX, int screenY, int pointer) {
            int rotation = Gdx.input.getRotation();
            int oy;
            if (rotation == 90) {
                oy = screenX;
                screenX = screenY;
                screenY = Gdx.graphics.getHeight() - oy;
            } else if (rotation == 270) {
                oy = screenY;
                screenY = screenX;
                screenX = Gdx.graphics.getWidth() - oy;
            }

            Vector3 touch = new Vector3((float) screenX, (float) screenY, 0.0F);
            MyGdxGame.this.camera.unproject(touch);
            int delX = (int) touch.x - screenX;
            int delY = (int) touch.y - screenY;
            if (pointer < 2) {
                GameMidlet.gameCanvas.pointerDragged(screenX + delX, screenY + delY, pointer);
            }

            return false;
        }

        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}
