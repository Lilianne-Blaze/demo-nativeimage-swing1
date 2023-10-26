# demo-nativeimage-swing1

Simple demo for small features making GraalVM native images more Windows-friendly.

Started due to almost complete lack of info about things like that, and my frustration about GraalVM treating GUI apps as third-class citizens.

I assume you already know the basics of GraalVM, and have an environment on which you can at least compile "hello world" examples.

# Features

Making app request admin rights on start.

Preventing app from starting a text console if it's not already present.

Changing main icon.

Changing version info.

# Requirements

Pretty much any distribution of GraalVM for Java 21 should work.

The configuration I'm writing it on is:

Windows 11 Pro,
Netbeans 18,
Visual Studio Community 2022,
Resource Hacker 5.1.7,
Liberica NIK 23.1.0 Full

# Questions

## How to make an app request admin rights?

Add the following to native-maven-plugin configuration:

```
<arg>-H:NativeLinkerOption=/MANIFEST:EMBED</arg>
<arg>-H:NativeLinkerOption=/MANIFESTUAC:level='requireAdministrator'</arg>
```

See:
https://learn.microsoft.com/en-us/cpp/build/reference/manifestuac-embeds-uac-information-in-manifest?view=msvc-170

## How to prevent an app from starting a text console?

Add the following to native-maven-plugin configuration:

```
<arg>-H:NativeLinkerOption=/SUBSYSTEM:WINDOWS</arg>
<arg>-H:NativeLinkerOption=/ENTRY:mainCRTStartup</arg>
```

See:
https://learn.microsoft.com/en-us/cpp/build/reference/entry-entry-point-symbol?view=msvc-170

## Why use AWT/Swing when JavaFX is available? Or why mix them?

JavaFX still doesn't have some basic functionality, such as tray icon support.

## How to add custom main icon? How to add custom version info?

Add something like that to native-maven-plugin configuration:

```
<arg>-H:NativeLinkerOption=${project.basedir}\src\main\resources\windows\mainicon.res</arg>
<arg>-H:NativeLinkerOption=${project.basedir}\src\main\resources\windows\ver.res</arg>
```

.res files contain various pieces of information that can be merged into a Windows exe.
They can contain all kinds of stuff, but we're mostly interested in these two.

You can make them with Resource Hacker (which is freeware), either entirely in it's GUI or using it as a compiler for .rc files.
.rc files are human-readable definitions.

.ico files are specific to Windows, they're a special kind of bitmap files with certain icon-specific requirements.
The easiest way to make them is with Paint.NET with IconCreator plugin. GIMP supports them too.
If you make them from scratch you probably want them to be 64x64 or 32x32.

No, you can't use .png or .jpg files, it has to be .ico format. Of course, you can convert other formats to .ico.

## All of that looks more like C++ stuff, why?

You're getting the point. It is "C++ stuff", which IMHO should be exposed by GraalVM build tools. Instead you have to use things like "-H:NativeLinkerOption" to forward extra instructions to native Windows build tools.

If you have any further questions you're more likely to find the answers on Microsoft site.

## Wait a sec, will that work with Windows 7? Or Windows 12?

Hopefully. I see no reasons why it shouldn't, but there are no guarantees either.

## Will it work with Windows 32-bit?

No. GraalVM native images support only 64-bit Windows.

## Will it work with Java 17 or some other version? Why did you choose 21?

I don't see why not. Pretty much all of it is using "-H:NativeLinkerOption" to give extra instructions to the linker. Should work with Java 11 too.

Because it was the latest LTS at the time of writing.

## Why the .cmd files instead of doing it inside pom.xml?

It's supposed to be easy to understand, not a "best practices".

## Why do you use /META-INF/native-image/${project.groupId}/${project.artifactId}/generated instead of /META-INF/native-image/${project.groupId}/${project.artifactId}?

Personal preference. The idea is to gather data from the agent into /generated folder, then move selected portions into a sibling /manual folder, with GraalVM using both.

## Can I do x? What is the license?

Personally I consider stuff like that "too simple to claim ownership to".

MIT. Basically "do whatever, don't blame me".

## Why am I getting the impression that you don't like GraalVM very much?

I want to like it. But after all that waiting I still don't think it's production-ready, at least not for GUI apps.
If it were that project would be completely unnecessary.

## This project helped me save countless gruelling hours of googling, how can I thank you?

Feel free to donate:

https://www.buymeacoffee.com/lblaze

https://paypal.me/lblaze216



