package telran.menu;

public interface Item {
String displayName();
void perform();
default boolean isFinished() {
	return false;
}
}
