package com.taamc.PlayerDinger;

import org.bukkit.Sound;
import org.bukkit.command.CommandException;

public class SoundUtil {
	/**
	 * Attempts to match a sound type. The sender requesting a creature type
	 * match. Can be null.
	 * 
	 * @param filter
	 */
	public static Sound matchSoundType(String filter) {

		Sound partialMatch = null;

		for (Sound type : Sound.values()) {
			if (type.name().replace("_", "")
					.equalsIgnoreCase(filter.replace("_", ""))) {
				return type;
			}

			if (type.name() != null) {
				if (type.name().equalsIgnoreCase(filter)) {
					return type;
				}

				if (type.name().toLowerCase().startsWith(filter.toLowerCase())) {
					partialMatch = type;
				}
			}

			if (type.name().replace("_", "")
					.equalsIgnoreCase(filter.replace("_", ""))
					|| (type.name() != null && type.name().equalsIgnoreCase(
							filter))) {
				return type;
			}
		}

		if (partialMatch != null) {
			return partialMatch;
		}

		throw new CommandException("Unknown sound specified! You can "
				+ "choose from the list of: " + getSoundNameList());
	}

	/**
	 * Get a list of Sound names.
	 * 
	 * @param requireSpawnable
	 *            Whether to only show entries that are spawnable
	 * @return
	 */
	public static String getSoundNameList() {
		StringBuilder str = new StringBuilder();
		for (Sound type : Sound.values()) {
			Class<?> entityClass = type.getClass();
			if (entityClass == null) {
				continue;
			}

			if (str.length() > 0) {
				str.append(", ");
			}
			str.append(type.name());

		}

		return str.toString();
	}
}