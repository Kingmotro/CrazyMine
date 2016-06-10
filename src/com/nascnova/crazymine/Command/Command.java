package com.nascnova.crazymine.Command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
	public String name();
	public int arglength();
	public String perm() default "cm.command";
	public String permMessage() default "You don''t have permission to do that.";
	public boolean opBypass() default true;
	public boolean onlyPlayer() default false;
	public String noPlayerMessage() default "This command can only run by a player.";
	public boolean main() default false;
	public String usage() default "Wrong usage.";
}
