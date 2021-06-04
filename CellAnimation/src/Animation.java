import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Animation {
	public BufferedImage img;

	public int cellsAlive = 0;

	private int[] pixels, field;

	private final Dimension SIZE;

	private int amountToDie = 3;

	private int maxSpawnAmount = 2;

	public Animation(int w, int h) {
		SIZE = new Dimension(w, h);

		img = new BufferedImage(SIZE.width, SIZE.height, BufferedImage.TYPE_INT_RGB);

		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

		for (int a = 0; a < pixels.length; a++) {
			pixels[a] = Color.lightGray.getRGB();
		}

		field = new int[pixels.length];

		int pos = (int) (Math.random() * field.length);

		field[pos] = 1;

		cellsAlive = 1;
	}

	public void updateField() {
		/*
		 * cycles through every cell
		 */
		for (int a = 0; a < field.length; a++) {
			/*
			 * checks if cell at a is still alive
			 */
			if (field[a] == 1 || field[a] == -1) {
				int cellCount = 0;
				try {
					if (field[a - SIZE.width - 1] == 1 || field[a] == -1) {
						cellCount++;
					}
				} catch (Exception e) {
				}

				try {
					if (field[a - SIZE.width] == 1 || field[a] == -1) {
						cellCount++;
					}
				} catch (Exception e) {
				}

				try {
					if (field[a - SIZE.width + 1] == 1 || field[a] == -1) {
						cellCount++;
					}
				} catch (Exception e) {
				}

				try {
					if (field[a + 1] == 1 || field[a] == -1) {
						cellCount++;
					}
				} catch (Exception e) {
				}

				try {
					if (field[a + SIZE.width + 1] == 1 || field[a] == -1) {
						cellCount++;
					}
				} catch (Exception e) {
				}

				try {
					if (field[a + SIZE.width] == 1 || field[a] == -1) {
						cellCount++;
					}
				} catch (Exception e) {
				}

				try {
					if (field[a + SIZE.width - 1] == 1 || field[a] == -1) {
						cellCount++;
					}
				} catch (Exception e) {
				}

				try {
					if (field[a - 1] == 1 || field[a] == -1) {
						cellCount++;
					}
				} catch (Exception e) {
				}

				/*
				 * spawns a new cell if cell has less then four neighbors
				 */
				if (cellCount <= maxSpawnAmount) {
					int pos = (int) (Math.random() * 8);

					try {
						switch (pos) {
						case 0:
							field[a - SIZE.width - 1] = 1;
							break;

						case 1:
							field[a - SIZE.width] = 1;
							break;

						case 2:
							field[a - SIZE.width + 1] = 1;
							break;

						case 3:
							field[a + 1] = 1;
							break;

						case 4:
							field[a + SIZE.width + 1] = 1;
							break;

						case 5:
							field[a + SIZE.width] = 1;
							break;

						case 6:
							field[a + SIZE.width - 1] = 1;
							break;
						default:
							field[a - 1] = 1;
							break;
						}
					} catch (Exception e) {
					}
				}

				/*
				 * kills cells with more then four neighbors
				 */
				if (cellCount > amountToDie) {
					field[a] = -1;
				}
			}
		}

		/*
		 * removes dead cells
		 */
		for (int a = 0; a < field.length; a++) {
			if (field[a] == -1) {
				field[a] = 0;
			}
		}

		/*
		 * overrides the image-array with the cell-array
		 */
		cellsAlive = 0;

		for (int a = 0; a < field.length; a++) {
			if (field[a] == 0) {
				pixels[a] = Color.lightGray.getRGB();
			} else {
				cellsAlive++;

				pixels[a] = Color.blue.getRGB();
			}
		}
	}
}
