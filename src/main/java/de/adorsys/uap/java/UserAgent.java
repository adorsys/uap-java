/**
 * Copyright 2012 Twitter, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.adorsys.uap.java;

import java.util.Map;

/**
 * User Agent parsed data class
 *
 * @author Steve Jiang (@sjiang) <gh at iamsteve com>
 */
public class UserAgent {
  public final String family, major, minor, patch, version, engine;

  public UserAgent(String family, String major, String minor, String patch, String engine) {
    this.family = family;
    this.major = major;
    this.minor = minor;
    this.patch = patch;
    this.engine = engine;
    this.version = fullVersion(major, minor, patch);
  }

  public static UserAgent fromMap(Map<String, String> m) {
    return new UserAgent(m.get("family"), m.get("major"), m.get("minor"), m.get("patch"), m.get("engine"));
  }

  /**
   * Combines the three version digits.
   * A bit hacky in this place but should work always no mather how the parsing-regex looks like
   */
  private String fullVersion(String majorVersion, String minorVersion, String patchVersion) {
	if (majorVersion == null) {
	  return "";
	}
    StringBuilder sb = new StringBuilder(majorVersion);
    if (minorVersion != null) {
      sb.append('.').append(minorVersion);
    }
	if (patchVersion != null) {
      sb.append('.').append(patchVersion);
	}
	return sb.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof UserAgent)) return false;

    UserAgent o = (UserAgent) other;
    return ((this.family != null && this.family.equals(o.family)) || this.family == o.family) &&
           ((this.major != null && this.major.equals(o.major)) || this.major == o.major) &&
           ((this.minor != null && this.minor.equals(o.minor)) || this.minor == o.minor) &&
           ((this.patch != null && this.patch.equals(o.patch)) || this.patch == o.patch) &&
           ((this.engine != null && this.engine.equals(o.engine)) || this.engine == o.engine);
  }

  @Override
  public int hashCode() {
    int h = family == null ? 0 : family.hashCode();
    h += major == null ? 0 : major.hashCode();
    h += minor == null ? 0 : minor.hashCode();
    h += patch == null ? 0 : patch.hashCode();
    h += engine == null ? 0 : engine.hashCode();
    return h;
  }

  @Override
  public String toString() {
    return String.format("{\"family\": %s, \"major\": %s, \"minor\": %s, \"patch\": %s, \"engine\": %s}",
                         family == null ? Constants.EMPTY_STRING : '"' + family + '"',
                         major == null ? Constants.EMPTY_STRING : '"' + major + '"',
                         minor == null ? Constants.EMPTY_STRING : '"' + minor + '"',
                         patch == null ? Constants.EMPTY_STRING : '"' + patch + '"',
    					 engine == null ? Constants.EMPTY_STRING : '"' + engine + '"');
  }

}