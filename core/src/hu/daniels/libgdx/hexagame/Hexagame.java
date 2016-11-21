package hu.daniels.libgdx.hexagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Hexagame extends ApplicationAdapter {
	PolygonSpriteBatch batch;

	@Override
	public void create () {
		batch = new PolygonSpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CORAL);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        TextureRegion textureRegion = new TextureRegion(texture);
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 2;
        float[] hexagonVertices = calcHexagonVertices(centerX, centerY, 150);
        short[] hexagonTriangles = getHexagonTriangles();
        PolygonRegion polygonRegion = new PolygonRegion(textureRegion, hexagonVertices, hexagonTriangles);
        PolygonSprite polygonSprite = new PolygonSprite(polygonRegion);
        polygonSprite.setOrigin(centerX, centerY);

        batch.begin();
        polygonSprite.draw(batch);
		batch.end();
        polygonSprite.rotate(10.1f);
	}

    private short[] getHexagonTriangles() {
        return new short[] {0, 2, 1,
                            0, 3, 2,
                            0, 4, 3,
                            0, 5, 4,
                            0, 6, 5,
                            0, 1, 6};
    }

    private float[] calcHexagonVertices(float x, float y, float size) {
        float[] vertices = new float[14];
        int vertexCounter = 0;
        vertices[vertexCounter++] = x;
        vertices[vertexCounter++] = y;
        for (int i = 0; i < 6; i++) {
            vertices[vertexCounter++] = x + size * MathUtils.sin((i * 60 + 30) * MathUtils.PI / 180);
            vertices[vertexCounter++] = y + size * MathUtils.cos((i * 60 + 30) * MathUtils.PI / 180);
        }
        return vertices;
    }

    @Override
	public void dispose () {
		batch.dispose();
	}
}
