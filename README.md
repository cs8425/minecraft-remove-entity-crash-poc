# A PoC plugin
PoC for remove `Entity` in `EntityRemoveFromWorldEvent` will cause `java.util.ConcurrentModificationException`.
The crash will only happened on 1.17, not 1.16.5.


## build

`mvn -X package`

## pre-build

* `build/PoC-1.0.0-crash.jar` : crash on 1.17, not 1.16.5
* `build/PoC-1.0.0-not-crash.jar`: not crash neither on 1.17, nor 1.16.5

## trigger

1. spawn lot of zombie
2. kill some by: `/kill @e[type=minecraft:zombie,distance=0..10]`
3. repeat multiple times until crash

### crash info

version: `This server is running Paper version git-Paper-46 (MC: 1.17) (Implementing API version 1.17-R0.1-SNAPSHOT) (Git: 9e07703)`

Server.log:

```
[13:16:06] [Server thread/INFO]: [<Player>: Killed 2 entities]
[13:16:08] [Server thread/ERROR]: Encountered an unexpected exception
net.minecraft.ReportedException: Exception ticking world
	at net.minecraft.server.MinecraftServer.tickChildren(MinecraftServer.java:1566) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.dedicated.DedicatedServer.tickChildren(DedicatedServer.java:477) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.MinecraftServer.tickServer(MinecraftServer.java:1404) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.MinecraftServer.runServer(MinecraftServer.java:1180) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.MinecraftServer.lambda$spin$0(MinecraftServer.java:320) ~[patched_1.17.jar:git-Paper-46]
	at java.lang.Thread.run(Thread.java:831) [?:?]
Caused by: java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013) ~[?:?]
	at java.util.ArrayList$Itr.next(ArrayList.java:967) ~[?:?]
	at com.google.common.collect.Iterators$3.next(Iterators.java:174) ~[patched_1.17.jar:git-Paper-46]
	at java.util.Iterator.forEachRemaining(Iterator.java:133) ~[?:?]
	at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801) ~[?:?]
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484) ~[?:?]
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474) ~[?:?]
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150) ~[?:?]
	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173) ~[?:?]
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[?:?]
	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596) ~[?:?]
	at net.minecraft.world.level.entity.PersistentEntitySectionManager.lambda$updateChunkStatus$6(PersistentEntitySectionManager.java:179) ~[patched_1.17.jar:git-Paper-46]
	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183) ~[?:?]
	at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179) ~[?:?]
	at java.util.stream.LongPipeline$1$1.accept(LongPipeline.java:177) ~[?:?]
	at java.util.PrimitiveIterator$OfLong.forEachRemaining(PrimitiveIterator.java:189) ~[?:?]
	at java.util.Spliterators$LongIteratorSpliterator.forEachRemaining(Spliterators.java:2002) ~[?:?]
	at java.util.Spliterator$OfLong.forEachRemaining(Spliterator.java:763) ~[?:?]
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484) ~[?:?]
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474) ~[?:?]
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150) ~[?:?]
	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173) ~[?:?]
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[?:?]
	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596) ~[?:?]
	at net.minecraft.world.level.entity.PersistentEntitySectionManager.updateChunkStatus(PersistentEntitySectionManager.java:164) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.world.level.entity.PersistentEntitySectionManager.updateChunkStatus(PersistentEntitySectionManager.java:150) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.level.ChunkTracker.onFullChunkStatusChange(ChunkTracker.java:2235) ~[?:?]
	at net.minecraft.server.level.ChunkHolder.demoteFullChunk(ChunkHolder.java:612) ~[?:?]
	at net.minecraft.server.level.ChunkHolder.updateFutures(ChunkHolder.java:760) ~[?:?]
	at net.minecraft.server.level.DistanceManager.runAllUpdates(DistanceManager.java:130) ~[?:?]
	at net.minecraft.server.level.ServerChunkCache.runDistanceManagerUpdates(ServerChunkCache.java:667) ~[?:?]
	at net.minecraft.server.level.ServerChunkCache.tick(ServerChunkCache.java:749) ~[?:?]
	at net.minecraft.server.level.ServerLevel.tick(ServerLevel.java:581) ~[?:?]
	at net.minecraft.server.MinecraftServer.tickChildren(MinecraftServer.java:1551) ~[patched_1.17.jar:git-Paper-46]
	... 5 more
[13:16:08] [Server thread/ERROR]: 	Cause of unexpected exception was
java.util.ConcurrentModificationException: null
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013) ~[?:?]
	at java.util.ArrayList$Itr.next(ArrayList.java:967) ~[?:?]
	at com.google.common.collect.Iterators$3.next(Iterators.java:174) ~[patched_1.17.jar:git-Paper-46]
	at java.util.Iterator.forEachRemaining(Iterator.java:133) ~[?:?]
	at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801) ~[?:?]
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484) ~[?:?]
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474) ~[?:?]
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150) ~[?:?]
	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173) ~[?:?]
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[?:?]
	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596) ~[?:?]
	at net.minecraft.world.level.entity.PersistentEntitySectionManager.lambda$updateChunkStatus$6(PersistentEntitySectionManager.java:179) ~[patched_1.17.jar:git-Paper-46]
	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183) ~[?:?]
	at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179) ~[?:?]
	at java.util.stream.LongPipeline$1$1.accept(LongPipeline.java:177) ~[?:?]
	at java.util.PrimitiveIterator$OfLong.forEachRemaining(PrimitiveIterator.java:189) ~[?:?]
	at java.util.Spliterators$LongIteratorSpliterator.forEachRemaining(Spliterators.java:2002) ~[?:?]
	at java.util.Spliterator$OfLong.forEachRemaining(Spliterator.java:763) ~[?:?]
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484) ~[?:?]
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474) ~[?:?]
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150) ~[?:?]
	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173) ~[?:?]
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234) ~[?:?]
	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596) ~[?:?]
	at net.minecraft.world.level.entity.PersistentEntitySectionManager.updateChunkStatus(PersistentEntitySectionManager.java:164) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.world.level.entity.PersistentEntitySectionManager.updateChunkStatus(PersistentEntitySectionManager.java:150) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.level.ChunkTracker.onFullChunkStatusChange(ChunkTracker.java:2235) ~[?:?]
	at net.minecraft.server.level.ChunkHolder.demoteFullChunk(ChunkHolder.java:612) ~[?:?]
	at net.minecraft.server.level.ChunkHolder.updateFutures(ChunkHolder.java:760) ~[?:?]
	at net.minecraft.server.level.DistanceManager.runAllUpdates(DistanceManager.java:130) ~[?:?]
	at net.minecraft.server.level.ServerChunkCache.runDistanceManagerUpdates(ServerChunkCache.java:667) ~[?:?]
	at net.minecraft.server.level.ServerChunkCache.tick(ServerChunkCache.java:749) ~[?:?]
	at net.minecraft.server.level.ServerLevel.tick(ServerLevel.java:581) ~[?:?]
	at net.minecraft.server.MinecraftServer.tickChildren(MinecraftServer.java:1551) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.dedicated.DedicatedServer.tickChildren(DedicatedServer.java:477) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.MinecraftServer.tickServer(MinecraftServer.java:1404) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.MinecraftServer.runServer(MinecraftServer.java:1180) ~[patched_1.17.jar:git-Paper-46]
	at net.minecraft.server.MinecraftServer.lambda$spin$0(MinecraftServer.java:320) ~[patched_1.17.jar:git-Paper-46]
	at java.lang.Thread.run(Thread.java:831) [?:?]
[13:16:08] [Server thread/ERROR]: This crash report has been saved to: .../mc-test/./crash-reports/crash-2021-06-25_13.16.08-server.txt
[13:16:08] [Server thread/INFO]: Stopping server
[13:16:08] [Server thread/INFO]: [PoC] Disabling PoC v1.0.0
[13:16:08] [Server thread/INFO]: Saving players
[13:16:08] [Server thread/INFO]: <Player> lost connection: Server closed
[13:16:08] [Server thread/INFO]: <Player> left the game
[13:16:08] [Server thread/INFO]: Saving worlds
[13:16:08] [Server thread/INFO]: Saving chunks for level 'ServerLevel[world]'/minecraft:overworld
[13:16:08] [Server thread/INFO]: ThreadedAnvilChunkStorage (world): All chunks are saved
[13:16:08] [Server thread/INFO]: Saving chunks for level 'ServerLevel[world_nether]'/minecraft:the_nether
[13:16:08] [Server thread/INFO]: ThreadedAnvilChunkStorage (DIM-1): All chunks are saved
[13:16:08] [Server thread/INFO]: Saving chunks for level 'ServerLevel[world_the_end]'/minecraft:the_end
[13:16:08] [Server thread/INFO]: ThreadedAnvilChunkStorage (DIM1): All chunks are saved
[13:16:08] [Server thread/INFO]: ThreadedAnvilChunkStorage (world): All chunks are saved
[13:16:08] [Server thread/INFO]: ThreadedAnvilChunkStorage (DIM-1): All chunks are saved
[13:16:08] [Server thread/INFO]: ThreadedAnvilChunkStorage (DIM1): All chunks are saved
[13:16:08] [Server thread/INFO]: Flushing Chunk IO
[13:16:08] [Server thread/INFO]: Closing Thread Pool
[13:16:08] [Server thread/INFO]: Closing Server
```

Crash Report:
```
---- Minecraft Crash Report ----
// I blame Dinnerbone.

Time: 6/25/21, 1:16 PM
Description: Exception ticking world

java.util.ConcurrentModificationException
	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
	at com.google.common.collect.Iterators$3.next(Iterators.java:174)
	at java.base/java.util.Iterator.forEachRemaining(Iterator.java:133)
	at java.base/java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at app//net.minecraft.world.level.entity.PersistentEntitySectionManager.lambda$updateChunkStatus$6(PersistentEntitySectionManager.java:179)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.LongPipeline$1$1.accept(LongPipeline.java:177)
	at java.base/java.util.PrimitiveIterator$OfLong.forEachRemaining(PrimitiveIterator.java:189)
	at java.base/java.util.Spliterators$LongIteratorSpliterator.forEachRemaining(Spliterators.java:2002)
	at java.base/java.util.Spliterator$OfLong.forEachRemaining(Spliterator.java:763)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at app//net.minecraft.world.level.entity.PersistentEntitySectionManager.updateChunkStatus(PersistentEntitySectionManager.java:164)
	at app//net.minecraft.world.level.entity.PersistentEntitySectionManager.updateChunkStatus(PersistentEntitySectionManager.java:150)
	at app//net.minecraft.server.level.ChunkTracker.onFullChunkStatusChange(ChunkTracker.java:2235)
	at app//net.minecraft.server.level.ChunkHolder.demoteFullChunk(ChunkHolder.java:612)
	at app//net.minecraft.server.level.ChunkHolder.updateFutures(ChunkHolder.java:760)
	at app//net.minecraft.server.level.DistanceManager.runAllUpdates(DistanceManager.java:130)
	at app//net.minecraft.server.level.ServerChunkCache.runDistanceManagerUpdates(ServerChunkCache.java:667)
	at app//net.minecraft.server.level.ServerChunkCache.tick(ServerChunkCache.java:749)
	at app//net.minecraft.server.level.ServerLevel.tick(ServerLevel.java:581)
	at app//net.minecraft.server.MinecraftServer.tickChildren(MinecraftServer.java:1551)
	at app//net.minecraft.server.dedicated.DedicatedServer.tickChildren(DedicatedServer.java:477)
	at app//net.minecraft.server.MinecraftServer.tickServer(MinecraftServer.java:1404)
	at app//net.minecraft.server.MinecraftServer.runServer(MinecraftServer.java:1180)
	at app//net.minecraft.server.MinecraftServer.lambda$spin$0(MinecraftServer.java:320)
	at java.base/java.lang.Thread.run(Thread.java:831)


A detailed walkthrough of the error, its code path and all known details is as follows:
---------------------------------------------------------------------------------------

-- Head --
Thread: Server thread
Stacktrace:
	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
	at com.google.common.collect.Iterators$3.next(Iterators.java:174)
	at java.base/java.util.Iterator.forEachRemaining(Iterator.java:133)
	at java.base/java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at app//net.minecraft.world.level.entity.PersistentEntitySectionManager.lambda$updateChunkStatus$6(PersistentEntitySectionManager.java:179)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.stream.LongPipeline$1$1.accept(LongPipeline.java:177)
	at java.base/java.util.PrimitiveIterator$OfLong.forEachRemaining(PrimitiveIterator.java:189)
	at java.base/java.util.Spliterators$LongIteratorSpliterator.forEachRemaining(Spliterators.java:2002)
	at java.base/java.util.Spliterator$OfLong.forEachRemaining(Spliterator.java:763)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:484)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
	at app//net.minecraft.world.level.entity.PersistentEntitySectionManager.updateChunkStatus(PersistentEntitySectionManager.java:164)
	at app//net.minecraft.world.level.entity.PersistentEntitySectionManager.updateChunkStatus(PersistentEntitySectionManager.java:150)
	at app//net.minecraft.server.level.ChunkMap.onFullChunkStatusChange(ChunkMap.java:2235)
	at app//net.minecraft.server.level.ChunkHolder.demoteFullChunk(ChunkHolder.java:612)
	at app//net.minecraft.server.level.ChunkHolder.updateFutures(ChunkHolder.java:760)
	at app//net.minecraft.server.level.DistanceManager.runAllUpdates(DistanceManager.java:130)
	at app//net.minecraft.server.level.ServerChunkCache.runDistanceManagerUpdates(ServerChunkCache.java:667)
	at app//net.minecraft.server.level.ServerChunkCache.tick(ServerChunkCache.java:749)
	at app//net.minecraft.server.level.ServerLevel.tick(ServerLevel.java:581)

-- Affected level --
Details:
	All players: 1 total; [EntityPlayer['<Player>'/395, uuid='<Player-UUID>', l='ServerLevel[world]', x=151.04, y=75.62, z=-175.08, cpos=[9, -11], tl=9572, v=true, rR=null](<Player> at 151.04148669122398,75.6183020148442,-175.08356911840218)]
	Chunk stats: 2242
	Level dimension: minecraft:overworld
	Level spawn location: World: (160,71,-192), Section: (at 0,7,0 in 10,4,-12; chunk contains blocks 160,0,-192 to 175,255,-177), Region: (0,-1; contains chunks 0,-32 to 31,-1, blocks 0,0,-512 to 511,255,-1)
	Level time: 1479848 game time, 1479848 day time
	Level name: world
	Level game mode: Game mode: survival (ID 0). Hardcore: false. Cheats: false
	Level weather: Rain time: 55421 (now: false), thunder time: 26478 (now: false)
	Known server brands: Paper
	Level was modded: true
	Level storage version: 0x04ABD - Anvil
Stacktrace:
	at net.minecraft.server.MinecraftServer.b(MinecraftServer.java:1565)
	at net.minecraft.server.dedicated.DedicatedServer.b(DedicatedServer.java:477)
	at net.minecraft.server.MinecraftServer.a(MinecraftServer.java:1404)
	at net.minecraft.server.MinecraftServer.x(MinecraftServer.java:1180)
	at net.minecraft.server.MinecraftServer.lambda$spin$0(MinecraftServer.java:320)
	at java.base/java.lang.Thread.run(Thread.java:831)

-- System Details --
Details:
	Minecraft Version: 1.17
	Minecraft Version ID: 1.17
	Operating System: Linux (amd64) version 5.4.0-70-generic
	Java Version: 16.0.1, Private Build
	Java VM Version: OpenJDK 64-Bit Server VM (mixed mode, sharing), Private Build
	Memory: 2088336416 bytes (1991 MiB) / 4294967296 bytes (4096 MiB) up to 8589934592 bytes (8192 MiB)
	CPUs: 8
	Processor Vendor: GenuineIntel
	Processor Name: Intel(R) Core(TM) i3-10100 CPU @ 3.60GHz
	Identifier: Intel64 Family 6 Model 165 Stepping 3
	Microarchitecture: unknown
	Frequency (GHz): 3.60
	Number of physical packages: 1
	Number of physical CPUs: 4
	Number of logical CPUs: 8
	Graphics card #0 name: Device
	Graphics card #0 vendor: Intel Corporation (0x8086)
	Graphics card #0 VRAM (MB): 256.00
	Graphics card #0 deviceId: 0x9bc8
	Graphics card #0 versionInfo: unknown
	Virtual memory max (MB): 20075.50
	Virtual memory used (MB): 14879.97
	Swap memory total (MB): 4096.00
	Swap memory used (MB): 7.50
	JVM Flags: 20 total; -Xms4G -Xmx8G -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=40 -XX:G1MaxNewSizePercent=50 -XX:G1HeapRegionSize=16M -XX:G1ReservePercent=15 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=20 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1
	CraftBukkit Information: 
   Running: Paper version git-Paper-46 (MC: 1.17) (Implementing API version 1.17-R0.1-SNAPSHOT) true
   Plugins: { PoC v1.0.0 net.sauceburnseal.PoC.P [thatguy],}
   Warnings: DEFAULT
   Reload Count: 0
   Threads: { WAITING Paper Async Chunk Task Thread #6: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], WAITING Paper Async Chunk Task Thread #5: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], RUNNABLE Netty Epoll Server IO #1: [app//io.netty.channel.epoll.Native.epollWait(Native Method), app//io.netty.channel.epoll.Native.epollWait(Native.java:192), app//io.netty.channel.epoll.Native.epollWait(Native.java:185), app//io.netty.channel.epoll.EpollEventLoop.epollWaitNoTimerChange(EpollEventLoop.java:290), app//io.netty.channel.epoll.EpollEventLoop.run(EpollEventLoop.java:347), app//io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989), app//io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], TIMED_WAITING IO-Worker-9: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:252), java.base@16.0.1/java.util.concurrent.SynchronousQueue$TransferStack.awaitFulfill(SynchronousQueue.java:462), java.base@16.0.1/java.util.concurrent.SynchronousQueue$TransferStack.transfer(SynchronousQueue.java:361), java.base@16.0.1/java.util.concurrent.SynchronousQueue.poll(SynchronousQueue.java:937), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1055), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], TIMED_WAITING IO-Worker-10: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:252), java.base@16.0.1/java.util.concurrent.SynchronousQueue$TransferStack.awaitFulfill(SynchronousQueue.java:462), java.base@16.0.1/java.util.concurrent.SynchronousQueue$TransferStack.transfer(SynchronousQueue.java:361), java.base@16.0.1/java.util.concurrent.SynchronousQueue.poll(SynchronousQueue.java:937), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1055), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], TIMED_WAITING process reaper: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:252), java.base@16.0.1/java.util.concurrent.SynchronousQueue$TransferStack.awaitFulfill(SynchronousQueue.java:462), java.base@16.0.1/java.util.concurrent.SynchronousQueue$TransferStack.transfer(SynchronousQueue.java:361), java.base@16.0.1/java.util.concurrent.SynchronousQueue.poll(SynchronousQueue.java:937), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1055), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], WAITING Worker-Main-3: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:341), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(AbstractQueuedSynchronizer.java:505), java.base@16.0.1/java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3137), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1614), java.base@16.0.1/java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:435), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], WAITING Paper Object Cleaner: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:341), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(AbstractQueuedSynchronizer.java:505), java.base@16.0.1/java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3137), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1614), java.base@16.0.1/java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:435), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], RUNNABLE Server console handler: [java.base@16.0.1/java.io.FileInputStream.read0(Native Method), java.base@16.0.1/java.io.FileInputStream.read(FileInputStream.java:223), app//org.jline.terminal.impl.AbstractPty$PtyInputStream.read(AbstractPty.java:73), app//org.jline.utils.NonBlockingInputStream.read(NonBlockingInputStream.java:62), app//org.jline.utils.NonBlocking$NonBlockingInputStreamReader.read(NonBlocking.java:168), app//org.jline.utils.NonBlockingReader.read(NonBlockingReader.java:57), app//org.jline.keymap.BindingReader.readCharacter(BindingReader.java:133), app//org.jline.keymap.BindingReader.readBinding(BindingReader.java:110), app//org.jline.keymap.BindingReader.readBinding(BindingReader.java:61), app//org.jline.reader.impl.LineReaderImpl.doReadBinding(LineReaderImpl.java:848), app//org.jline.reader.impl.LineReaderImpl.readBinding(LineReaderImpl.java:868), app//org.jline.reader.impl.LineReaderImpl.readLine(LineReaderImpl.java:575), app//org.jline.reader.impl.LineReaderImpl.readLine(LineReaderImpl.java:418), app//net.minecrell.terminalconsole.SimpleTerminalConsole.readCommands(SimpleTerminalConsole.java:158), app//net.minecrell.terminalconsole.SimpleTerminalConsole.start(SimpleTerminalConsole.java:141), app//net.minecraft.server.dedicated.DedicatedServer$1.run(DedicatedServer.java:112)], RUNNABLE Notification Thread: [], WAITING Snooper Timer: [java.base@16.0.1/java.lang.Object.wait(Native Method), java.base@16.0.1/java.lang.Object.wait(Object.java:320), java.base@16.0.1/java.util.TimerThread.mainLoop(Timer.java:527), java.base@16.0.1/java.util.TimerThread.run(Timer.java:506)], WAITING Worker-Main-4: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:341), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(AbstractQueuedSynchronizer.java:505), java.base@16.0.1/java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3137), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1614), java.base@16.0.1/java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:435), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], WAITING Paper Async Chunk Task Thread #4: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], RUNNABLE Netty Epoll Server IO #0: [app//io.netty.channel.epoll.Native.epollWait(Native Method), app//io.netty.channel.epoll.Native.epollWait(Native.java:192), app//io.netty.channel.epoll.Native.epollWait(Native.java:185), app//io.netty.channel.epoll.EpollEventLoop.epollWaitNoTimerChange(EpollEventLoop.java:290), app//io.netty.channel.epoll.EpollEventLoop.run(EpollEventLoop.java:347), app//io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989), app//io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], TIMED_WAITING Cleaner-0: [java.base@16.0.1/java.lang.Object.wait(Native Method), java.base@16.0.1/java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:155), java.base@16.0.1/jdk.internal.ref.CleanerImpl.run(CleanerImpl.java:140), java.base@16.0.1/java.lang.Thread.run(Thread.java:831), java.base@16.0.1/jdk.internal.misc.InnocuousThread.run(InnocuousThread.java:134)], WAITING Worker-Main-5: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:341), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(AbstractQueuedSynchronizer.java:505), java.base@16.0.1/java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3137), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1614), java.base@16.0.1/java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:435), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], WAITING Paper Async Chunk Urgent Task Thread: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], WAITING Craft Async Scheduler Management Thread: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:341), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(AbstractQueuedSynchronizer.java:505), java.base@16.0.1/java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3137), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1614), java.base@16.0.1/java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:435), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], TIMED_WAITING Log4j2-TF-1-AsyncLogger[AsyncContext@14514713]-1: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:252), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:1661), app//com.lmax.disruptor.TimeoutBlockingWaitStrategy.waitFor(TimeoutBlockingWaitStrategy.java:38), app//com.lmax.disruptor.ProcessingSequenceBarrier.waitFor(ProcessingSequenceBarrier.java:56), app//com.lmax.disruptor.BatchEventProcessor.processEvents(BatchEventProcessor.java:159), app//com.lmax.disruptor.BatchEventProcessor.run(BatchEventProcessor.java:125), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], WAITING Worker-Main-6: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:341), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(AbstractQueuedSynchronizer.java:505), java.base@16.0.1/java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3137), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1614), java.base@16.0.1/java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:435), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], WAITING Worker-Main-1: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:341), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(AbstractQueuedSynchronizer.java:505), java.base@16.0.1/java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3137), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1614), java.base@16.0.1/java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:435), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], WAITING Paper Async Chunk Task Thread #3: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], WAITING Paper Async Chunk Task Thread #0: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], WAITING Finalizer: [java.base@16.0.1/java.lang.Object.wait(Native Method), java.base@16.0.1/java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:155), java.base@16.0.1/java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:176), java.base@16.0.1/java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:171)], RUNNABLE DestroyJavaVM: [], WAITING Paper RegionFile IO Thread: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], WAITING Paper Async Chunk Task Thread #2: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], TIMED_WAITING Timer hack thread: [java.base@16.0.1/java.lang.Thread.sleep(Native Method), app//net.minecraft.SystemUtils$4.run(SystemUtils.java:581)], RUNNABLE Server thread: [java.base@16.0.1/java.lang.Thread.dumpThreads(Native Method), java.base@16.0.1/java.lang.Thread.getAllStackTraces(Thread.java:1647), app//org.bukkit.craftbukkit.v1_17_R1.CraftCrashReport.get(CraftCrashReport.java:33), app//org.bukkit.craftbukkit.v1_17_R1.CraftCrashReport.get(CraftCrashReport.java:17), app//net.minecraft.SystemReport.a(SystemReport.java:65), app//net.minecraft.CrashReport.<init>(CrashReport.java:35), app//net.minecraft.CrashReport.a(CrashReport.java:245), app//net.minecraft.server.MinecraftServer.b(MinecraftServer.java:1558), app//net.minecraft.server.dedicated.DedicatedServer.b(DedicatedServer.java:477), app//net.minecraft.server.MinecraftServer.a(MinecraftServer.java:1404), app//net.minecraft.server.MinecraftServer.x(MinecraftServer.java:1180), app//net.minecraft.server.MinecraftServer.lambda$spin$0(MinecraftServer.java:320), app//net.minecraft.server.MinecraftServer$$Lambda$3680/0x000000080138b288.run(Unknown Source), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], TIMED_WAITING Paper Watchdog Thread: [java.base@16.0.1/java.lang.Thread.sleep(Native Method), app//org.spigotmc.WatchdogThread.run(WatchdogThread.java:170)], WAITING ForkJoinPool.commonPool-worker-3: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), java.base@16.0.1/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1633), java.base@16.0.1/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:183)], WAITING Paper Async Chunk Task Thread #1: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:211), app//com.destroystokyo.paper.io.QueueExecutorThread.run(QueueExecutorThread.java:84)], TIMED_WAITING Common-Cleaner: [java.base@16.0.1/java.lang.Object.wait(Native Method), java.base@16.0.1/java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:155), java.base@16.0.1/jdk.internal.ref.CleanerImpl.run(CleanerImpl.java:140), java.base@16.0.1/java.lang.Thread.run(Thread.java:831), java.base@16.0.1/jdk.internal.misc.InnocuousThread.run(InnocuousThread.java:134)], TIMED_WAITING pool-6-thread-1: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:252), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos(AbstractQueuedSynchronizer.java:1661), java.base@16.0.1/java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1182), java.base@16.0.1/java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:899), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)], RUNNABLE Reference Handler: [java.base@16.0.1/java.lang.ref.Reference.waitForReferencePendingList(Native Method), java.base@16.0.1/java.lang.ref.Reference.processPendingReferences(Reference.java:243), java.base@16.0.1/java.lang.ref.Reference$ReferenceHandler.run(Reference.java:215)], RUNNABLE Signal Dispatcher: [], WAITING Worker-Main-2: [java.base@16.0.1/jdk.internal.misc.Unsafe.park(Native Method), java.base@16.0.1/java.util.concurrent.locks.LockSupport.park(LockSupport.java:341), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionNode.block(AbstractQueuedSynchronizer.java:505), java.base@16.0.1/java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3137), java.base@16.0.1/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:1614), java.base@16.0.1/java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:435), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1056), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1116), java.base@16.0.1/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630), java.base@16.0.1/java.lang.Thread.run(Thread.java:831)],}
   
   Force Loaded Chunks: { world: {}, world_nether: {}, world_the_end: {},}
	Player Count: 1 / 200; [EntityPlayer['<Player>'/395, uuid='<Player-UUID>', l='ServerLevel[world]', x=151.04, y=75.62, z=-175.08, cpos=[9, -11], tl=9572, v=true, rR=null](<Player> at 151.04148669122398,75.6183020148442,-175.08356911840218)]
	Data Packs: vanilla, file/bukkit
	Is Modded: Definitely; Server brand changed to 'Paper'
	Type: Dedicated Server (map_server.txt)
```
