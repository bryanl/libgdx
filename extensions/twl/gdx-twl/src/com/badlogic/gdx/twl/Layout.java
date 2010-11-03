
package com.badlogic.gdx.twl;

import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Widget;

public class Layout extends DialogLayout {
	public Layout () {
		setTheme("");
	}

	public Direction horizontal () {
		return new Direction(true);
	}

	public Direction vertical () {
		return new Direction(false);
	}

	public class Direction {
		private final boolean horizontal;

		Direction (boolean horizontal) {
			this.horizontal = horizontal;
		}

		public Group sequence (Object... widgets) {
			DialogLayout.Group dialogGroup = createSequentialGroup();
			if (horizontal)
				setHorizontalGroup(dialogGroup);
			else
				setVerticalGroup(dialogGroup);
			return new Group(null, dialogGroup).add(widgets);
		}

		public Group parallel (Object... widgets) {
			DialogLayout.Group dialogGroup = createParallelGroup();
			if (horizontal)
				setHorizontalGroup(dialogGroup);
			else
				setVerticalGroup(dialogGroup);
			return new Group(null, dialogGroup).add(widgets);
		}

		public class Group {
			private final Group parent;
			private final DialogLayout.Group dialogGroup;

			Group (Group parent, DialogLayout.Group dialogGroup) {
				this.parent = parent;
				this.dialogGroup = dialogGroup;
			}

			public Group sequence (Object... widgets) {
				DialogLayout.Group dialogGroup = createSequentialGroup();
				this.dialogGroup.addGroup(dialogGroup);
				return new Group(this, dialogGroup).add(widgets);
			}

			public Group parallel (Object... widgets) {
				DialogLayout.Group dialogGroup = createParallelGroup();
				this.dialogGroup.addGroup(dialogGroup);
				return new Group(this, dialogGroup).add(widgets);
			}

			public Group add (Object... widgets) {
				for (int i = 0, n = widgets.length; i < n; i++) {
					Object object = widgets[i];
					if (object instanceof Integer) {
						int size = (Integer)object;
						if (size == 0)
							dialogGroup.addGap();
						else
							dialogGroup.addGap(size);
					} else
						dialogGroup.addWidget((Widget)object);
				}
				return this;
			}

			public Group add (String gapName, Widget... widgets) {
				dialogGroup.addWidgetsWithGap(gapName, widgets);
				return this;
			}

			public Group gap () {
				dialogGroup.addGap();
				return this;
			}

			public Group gap (int size) {
				dialogGroup.addGap(size);
				return this;
			}

			public Group gap (int min, int pref, int max) {
				dialogGroup.addGap(min, pref, max);
				return this;
			}

			public Group gap (String name) {
				dialogGroup.addGap(name);
				return this;
			}

			public Group defaulGap () {
				dialogGroup.addDefaultGap();
				return this;
			}

			public Group minGap (int size) {
				dialogGroup.addMinGap(size);
				return this;
			}

			public Group end () {
				return parent;
			}
		}
	}
}
