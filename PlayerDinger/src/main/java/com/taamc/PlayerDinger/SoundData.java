package com.taamc.PlayerDinger;

import org.apache.commons.lang.Validate;
import org.bukkit.Sound;

public class SoundData {
	float volume;
	float pitch;
	Sound sound;

	SoundData(Sound sound, float volume, float pitch) {
		Validate.notNull(sound, "Sound must exist");
		Validate.isTrue(0<=volume && volume<=2, "Volume must be between 0 and 2");
		Validate.isTrue(0<=pitch && pitch<=2, "Pitch must be between 0 and 2");
		this.volume = volume;
		this.pitch = pitch;
		this.sound = sound;
	}

	SoundData(String s, float v, float p) {
		this(SoundUtil.matchSoundType(s), v, p);
	}
}
