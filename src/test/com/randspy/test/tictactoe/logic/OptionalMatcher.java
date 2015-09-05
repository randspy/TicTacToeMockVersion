package com.randspy.test.tictactoe.logic;

// taken from https://github.com/npathai/hamcrest-jdk8-extras

import java.util.Optional;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Provides matchers for JDK 8 {@link Optional}
 * 
 * @author npathai
 *
 */
public abstract class OptionalMatcher<T> extends TypeSafeMatcher<Optional<T>> {

	/**
	 * @return a matcher which matches if Optional is present 
	 */
	public static <T> Matcher<Optional<T>> isPresent() {
		return new PresenceMatcher<T>();
	}

	private static class PresenceMatcher<T> extends OptionalMatcher<T> {
		
		public void describeTo(Description description) {
			description.appendText("<Present>");
		}

		@Override
		protected boolean matchesSafely(Optional<T> item) {
			return item.isPresent();
		}
		
		@Override
		protected void describeMismatchSafely(Optional<T> item, Description mismatchDescription) {
			mismatchDescription.appendText("was <Empty>");
		}
	}

	/**
	 * @return a matcher which matches if Optional is empty
	 */
	public static <T> Matcher<Optional<T>> isEmpty() {
		return new EmptyMatcher<T>();
	}
	
	private static class EmptyMatcher<T> extends OptionalMatcher<T> {

		public void describeTo(Description description) {
			description.appendText("<Empty>");
		}

		@Override
		protected boolean matchesSafely(Optional<T> item) {
			return !item.isPresent();
		}
		
		@Override
		protected void describeMismatchSafely(Optional<T> item, Description mismatchDescription) {
			mismatchDescription.appendText("was <Present> with value " + item.get());
		}
	}

	/**
	 * @return a matcher which matches if Optional is present and also matches 
	 * provided {@code matcher}
	 */
	public static <T> Matcher<Optional<T>> isPresentAnd(Matcher<? super T> matcher) {
		return new PresentAndMatcher<>(matcher);
	}
	
	private static class PresentAndMatcher<T> extends OptionalMatcher<T> {
		private PresenceMatcher<T> presenceMatcher = new PresenceMatcher<>();
		private Matcher<? super T> matcher;
		
		public PresentAndMatcher(Matcher<? super T> matcher) {
			this.matcher = matcher;
		}
		
		@Override
		public void describeTo(Description description) {
			presenceMatcher.describeTo(description);
			description.appendText(" and ");
			matcher.describeTo(description);
		}

		@Override
		protected boolean matchesSafely(Optional<T> item) {
			return presenceMatcher.matchesSafely(item) 
					&& matcher.matches(item.get());
		}
		
		@Override
		protected void describeMismatchSafely(Optional<T> item, Description mismatchDescription) {
			if (!presenceMatcher.matchesSafely(item)) {
				mismatchDescription.appendText("was <Empty>");
			} else {
				mismatchDescription.appendText("was <Present> and ");
				matcher.describeMismatch(item.get(), mismatchDescription);
			}
		}
	}
}
