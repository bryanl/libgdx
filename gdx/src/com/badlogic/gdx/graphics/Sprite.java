package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.MathUtils;

/**
 * Holds the geometry, color, and texture information for drawing 2D sprites
 * using {@link SpriteBatch}. A Sprite has a position and a size given as width
 * and height. The position is relative to the origin of the coordinate system
 * specified via {@link SpriteBatch#begin()} and the respective matrices. A
 * Sprite is always rectangular and its position (x, y) are located in the
 * bottom left corner of that rectangle. A Sprite also has an origin around
 * which rotations and scaling are performed. The origin is given relative to
 * the bottom left corner of the Sprite, its position. Texture information is given as pixels
 * and is always relative to texture space.
 * 
 * @author mzechner
 * @author Nathan Sweet <misc@n4te.com>
 */
public class Sprite {
	static final int VERTEX_SIZE = 2 + 1 + 2;
	static final int SPRITE_SIZE = 4 * VERTEX_SIZE;

	private Texture texture;
	private final float[] vertices = new float[20];
	private float x, y;
	private float width, height;
	private float originX, originY;
	private float rotation;
	private float scaleX = 1, scaleY = 1;
	private final Color color = new Color(1, 1, 1, 1);
	private boolean dirty;

	/**
	 * Creates an uninitialized sprite. The sprite will need a texture, texture
	 * region, bounds, and color set before it can be drawn.
	 */
	public Sprite() {
	}

	/**
	 * Creates a sprite with width, height, and texture region equal to the size
	 * of the texture.
	 */
	public Sprite(Texture texture) {
		this(texture, 0, 0, texture.getWidth(), texture.getHeight());
	}

	/**
	 * Creates a sprite with width, height, and texture region equal to the
	 * specified size. The texture region's upper left corner will be 0,0.
	 * 
	 * @param srcWidth
	 *            The width of the texture region. May be negative to flip the
	 *            sprite when drawn.
	 * @param srcHeight
	 *            The height of the texture region. May be negative to flip the
	 *            sprite when drawn.
	 */
	public Sprite(Texture texture, int srcWidth, int srcHeight) {
		this(texture, 0, 0, srcWidth, srcHeight);
	}

	/**
	 * Creates a sprite with width, height, and texture region equal to the
	 * specified size.
	 * 
	 * @param srcWidth
	 *            The width of the texture region. May be negative to flip the
	 *            sprite when drawn.
	 * @param srcHeight
	 *            The height of the texture region. May be negative to flip the
	 *            sprite when drawn.
	 */
	public Sprite(Texture texture, int srcX, int srcY, int srcWidth,
			int srcHeight) {
		if (texture == null)
			throw new IllegalArgumentException("texture cannot be null.");
		this.texture = texture;
		setTextureRegion(srcX, srcY, srcWidth, srcHeight);
		setColor(1, 1, 1, 1);
		setSize(Math.abs(srcWidth), Math.abs(srcHeight));
		setOrigin(width / 2, height / 2);
	}

	/**
	 * Creates a sprite with width, height, and texture region equal to the
	 * specified size, relative to specified sprite's texture region.
	 * 
	 * @param srcWidth
	 *            The width of the texture region. May be negative to flip the
	 *            sprite when drawn.
	 * @param srcHeight
	 *            The height of the texture region. May be negative to flip the
	 *            sprite when drawn.
	 */
	public Sprite(Sprite parent, int srcX, int srcY, int srcWidth, int srcHeight) {
		this(parent.texture, (int) (srcX + parent.vertices[U1]
				* parent.texture.getWidth()), (int) (srcY + parent.vertices[V2]
				* parent.texture.getHeight()), srcWidth, srcHeight);
	}

	/**
	 * Creates a sprite that is a copy in every way of the specified sprite.
	 */
	public Sprite (Sprite sprite) {
		if (sprite == null) throw new IllegalArgumentException("sprite cannot be null.");
		texture = sprite.texture;
		System.arraycopy(sprite.vertices, 0, vertices, 0, 20);
		x = sprite.x;
		y = sprite.y;
		width = sprite.width;
		height = sprite.height;
		originX = sprite.originX;
		originY = sprite.originY;
		rotation = sprite.rotation;
		scaleX = sprite.scaleX;
		scaleY = sprite.scaleY;
		color.set(sprite.color);
		dirty = sprite.dirty;
	}

	/**
	 * Sets the position and size of the sprite when drawn, before scaling and
	 * rotation are applied. If origin, rotation, or scale are changed, it is
	 * slightly more efficient to set the bounds after those operations.
	 */
	public void setBounds(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		if (dirty)
			return;

		float x2 = x + width;
		float y2 = y + height;
		float[] vertices = this.vertices;
		vertices[X1] = x;
		vertices[Y1] = y;

		vertices[X2] = x;
		vertices[Y2] = y2;

		vertices[X3] = x2;
		vertices[Y3] = y2;

		vertices[X4] = x2;
		vertices[Y4] = y;

		if (rotation != 0 || scaleX != 1 || scaleY != 1)
			dirty = true;
	}

	/**
	 * Sets the size of the sprite when drawn, before scaling and rotation are
	 * applied. If origin, rotation, or scale are changed, it is slightly more
	 * efficient to set the size after those operations. If both position and
	 * size are to be changed, it is better to use
	 * {@link #setBounds(float, float, float, float)}.
	 */
	public void setSize(float width, float height) {
		this.width = width;
		this.height = height;

		if (dirty)
			return;

		float x2 = x + width;
		float y2 = y + height;
		float[] vertices = this.vertices;
		vertices[X1] = x;
		vertices[Y1] = y;

		vertices[X2] = x;
		vertices[Y2] = y2;

		vertices[X3] = x2;
		vertices[Y3] = y2;

		vertices[X4] = x2;
		vertices[Y4] = y;

		if (rotation != 0 || scaleX != 1 || scaleY != 1)
			dirty = true;
	}

	/**
	 * Sets the position where the sprite will be drawn. If origin, rotation, or
	 * scale are changed, it is slightly more efficient to set the position
	 * after those operations. If both position and size are to be changed, it
	 * is better to use {@link #setBounds(float, float, float, float)}.
	 */
	public void setPosition(float x, float y) {
		translate(x - this.x, y - this.y);
	}

	/**
	 * Sets the position relative to the current position where the sprite will
	 * be drawn. If origin, rotation, or scale are changed, it is slightly more
	 * efficient to translate after those operations.
	 */
	public void translate(float xAmount, float yAmount) {
		x += xAmount;
		y += yAmount;

		if (dirty)
			return;

		float[] vertices = this.vertices;
		vertices[X1] += xAmount;
		vertices[Y1] += yAmount;

		vertices[X2] += xAmount;
		vertices[Y2] += yAmount;

		vertices[X3] += xAmount;
		vertices[Y3] += yAmount;

		vertices[X4] += xAmount;
		vertices[Y4] += yAmount;
	}

	/**
	 * Sets the texture coordinates in pixels to apply to the sprite. This
	 * resets calling {@link #flip(boolean, boolean)}.
	 * 
	 * @param srcWidth
	 *            The width of the texture region. May be negative to flip the
	 *            sprite when drawn.
	 * @param srcHeight
	 *            The height of the texture region. May be negative to flip the
	 *            sprite when drawn.
	 */
	public void setTextureRegion(int srcX, int srcY, int srcWidth, int srcHeight) {
		float invTexWidth = 1.0f / texture.getWidth();
		float invTexHeight = 1.0f / texture.getHeight();
		float u = srcX * invTexWidth;
		float v = (srcY + srcHeight) * invTexHeight;
		float u2 = (srcX + srcWidth) * invTexWidth;
		float v2 = srcY * invTexHeight;

		float[] vertices = this.vertices;
		vertices[U1] = u;
		vertices[V1] = v;

		vertices[U2] = u;
		vertices[V2] = v2;

		vertices[U3] = u2;
		vertices[V3] = v2;

		vertices[U4] = u2;
		vertices[V4] = v;
	}

	/**
	 * Sets whether the texture will be repeated or stetched. The default is
	 * stretched.
	 * 
	 * @param x
	 *            If true, the texture will be repeated.
	 * @param y
	 *            If true, the texture will be repeated.
	 */
	public void setTextureRepeat(boolean x, boolean y) {
		texture.bind();
		GL10 gl = Gdx.gl10;
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				x ? GL10.GL_REPEAT : GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				y ? GL10.GL_REPEAT : GL10.GL_CLAMP_TO_EDGE);
	}

	/**
	 * Flips the current texture region.
	 */
	public void flip(boolean x, boolean y) {
		float[] vertices = this.vertices;
		if (x) {
			float u = vertices[U1];
			float u2 = vertices[U3];
			vertices[U1] = u2;
			vertices[U2] = u2;
			vertices[U3] = u;
			vertices[U4] = u;
		}
		if (y) {
			float v = vertices[V1];
			float v2 = vertices[V3];
			vertices[V1] = v2;
			vertices[V2] = v;
			vertices[V3] = v;
			vertices[V4] = v2;
		}
	}

	/**
	 * Offsets the texture region relative to the current texture region.
	 * 
	 * @param xAmount
	 *            The percentage to offset horizontally.
	 * @param yAmount
	 *            The percentage to offset vertically.
	 */
	public void scrollTexture(float xAmount, float yAmount) {
		float[] vertices = this.vertices;
		if (xAmount != 0) {
			float u = (vertices[U1] + xAmount) % 1;
			float u2 = u + width / texture.getWidth();
			vertices[U1] = u;
			vertices[U2] = u;
			vertices[U3] = u2;
			vertices[U4] = u2;
		}
		if (yAmount != 0) {
			float v = (vertices[V1] + yAmount) % 1;
			float v2 = v + height / texture.getHeight();
			vertices[V1] = v;
			vertices[V2] = v2;
			vertices[V3] = v2;
			vertices[V4] = v;
		}
	}

	public void setColor(Color tint) {
		float color = tint.toFloatBits();
		float[] vertices = this.vertices;
		vertices[C1] = color;
		vertices[C2] = color;
		vertices[C3] = color;
		vertices[C4] = color;
	}

	public void setColor(float r, float g, float b, float a) {
		int intBits = ((int) (255 * a) << 24) | //
				((int) (255 * b) << 16) | //
				((int) (255 * g) << 8) | //
				((int) (255 * r));
		float color = Float.intBitsToFloat(intBits);
		float[] vertices = this.vertices;
		vertices[C1] = color;
		vertices[C2] = color;
		vertices[C3] = color;
		vertices[C4] = color;
	}

	/**
	 * Sets the origin in relation to the sprite's position for scaling and
	 * rotation.
	 */
	public void setOrigin(float originX, float originY) {
		this.originX = originX;
		this.originY = originY;
		dirty = true;
	}

	public void setRotation(float degrees) {
		this.rotation = degrees;
		dirty = true;
	}

	/**
	 * Sets the sprite's rotation relative to the current rotation.
	 */
	public void rotate(float degrees) {
		rotation += degrees;
		dirty = true;
	}

	/**
	 * Rotates this sprite 90 degrees. This rotation is unaffected by {@link #setRotation(float)} and {@link #rotate(float)}.
	 */
	public void rotate90 (boolean clockwise) {
		float[] vertices = this.vertices;

		float temp = width;
		width = height;
		height = temp;

		temp = vertices[X1];
		vertices[X1] = vertices[X4];
		vertices[X4] = vertices[X3];
		vertices[X3] = vertices[X2];
		vertices[X2] = temp;
		
		temp = vertices[Y1];
		vertices[Y1] = vertices[Y4];
		vertices[Y4] = vertices[Y3];
		vertices[Y3] = vertices[Y2];
		vertices[Y2] = temp;

		temp = vertices[V1];
		vertices[V1] = vertices[V4];
		vertices[V4] = vertices[V3];
		vertices[V3] = vertices[V2];
		vertices[V2] = temp;

		temp = vertices[U1];
		vertices[U1] = vertices[U4];
		vertices[U4] = vertices[U3];
		vertices[U3] = vertices[U2];
		vertices[U2] = temp;

		if (rotation != 0 || scaleX != 1 || scaleY != 1) dirty = true;
	}

	public void setScale(float scaleXY) {
		this.scaleX = scaleXY;
		this.scaleY = scaleXY;
		dirty = true;
	}

	public void setScale(float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		dirty = true;
	}

	/**
	 * Sets the sprite's scale relative to the current scale.
	 */
	public void scale(float amount) {
		this.scaleX += amount;
		this.scaleY += amount;
		dirty = true;
	}

	/**
	 * Returns the packed vertices, colors, and texture coordinates for this sprite.
	 */
	public float[] getVertices () {
		if (dirty) {
			dirty = false;

			float[] vertices = this.vertices;
			float localX = -originX;
			float localY = -originY;
			float localX2 = localX + width;
			float localY2 = localY + height;
			float worldOriginX = this.x - localX;
			float worldOriginY = this.y - localY;
			if (scaleX != 1 || scaleY != 1) {
				localX *= scaleX;
				localY *= scaleY;
				localX2 *= scaleX;
				localY2 *= scaleY;
			}
			if (rotation != 0) {
				float cos = MathUtils.cosDeg(rotation);
				float sin = MathUtils.sinDeg(rotation);
	
				float x1 = localX * cos - localY * sin + worldOriginX;
				float y1 = localY * cos + localX * sin + worldOriginY;
				vertices[X1] = x1;
				vertices[Y1] = y1;
	
				float x2 = localX * cos - localY2 * sin + worldOriginX;
				float y2 = localY2 * cos + localX * sin + worldOriginY;
				vertices[X2] = x2;
				vertices[Y2] = y2;
	
				float x3 = localX2 * cos - localY2 * sin + worldOriginX;
				float y3 = localY2 * cos + localX2 * sin + worldOriginY;
				vertices[X3] = x3;
				vertices[Y3] = y3;
	
				vertices[X4] = x1 + (x3 - x2);
				vertices[Y4] = y3 - (y2 - y1);
			} else {
				float x1 = localX + worldOriginX;
				float y1 = localY + worldOriginY;
				float x2 = localX2 + worldOriginX;
				float y2 = localY2 + worldOriginY;
				
				vertices[X1] = x1;
				vertices[Y1] = y1;
	
				vertices[X2] = x1;
				vertices[Y2] = y2;
	
				vertices[X3] = x2;
				vertices[Y3] = y2;
	
				vertices[X4] = x2;
				vertices[Y4] = y1;
			}
		}
		return vertices;
	}

	public void draw(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, getVertices(), 0, 20);
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return texture;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getOriginX() {
		return originX;
	}

	public float getOriginY() {
		return originY;
	}

	public float getRotation() {
		return rotation;
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getTextureRegionX () {
		return vertices[U1];
	}
	
	public float getTextureRegionY () {
		return vertices[V1];
	}
	
	public float getTextureRegionWidth () {
		return vertices[U3] - vertices[U1];
	}
	
	public float getTextureRegionHeight () {
		return vertices[V1] - vertices[V2];
	}

	/**
	 * Returns the color for this sprite. Changing the returned color will have
	 * no affect. {@link #setColor(Color)} or
	 * {@link #setColor(float, float, float, float)} must be used.
	 */
	public Color getColor() {
		int intBits = Float.floatToIntBits(vertices[C1]);
		Color color = this.color;
		color.r = (intBits & 0xff) / 255f;
		color.g = (intBits >> 8 & 0xff) / 255f;
		color.b = (intBits >> 16 & 0xff) / 255f;
		color.a = (intBits >> 24) / 255f;
		return color;
	}

	static private final int X1 = 0;
	static private final int Y1 = 1;
	static private final int C1 = 2;
	static private final int U1 = 3;
	static private final int V1 = 4;
	static private final int X2 = 5;
	static private final int Y2 = 6;
	static private final int C2 = 7;
	static private final int U2 = 8;
	static private final int V2 = 9;
	static private final int X3 = 10;
	static private final int Y3 = 11;
	static private final int C3 = 12;
	static private final int U3 = 13;
	static private final int V3 = 14;
	static private final int X4 = 15;
	static private final int Y4 = 16;
	static private final int C4 = 17;
	static private final int U4 = 18;
	static private final int V4 = 19;
}
