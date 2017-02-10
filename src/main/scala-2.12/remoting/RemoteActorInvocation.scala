package remoting

import akka.actor.{ActorRef, ActorSystem, Props}

/**
  * Created by bpant on 09/02/17.
  */
object RemoteActorInvocation extends App {
  val system = ActorSystem("actor-system-remote")
  val remoteActor: ActorRef = system.actorOf(Props[RemoteActor], name = "remoteActor")
  remoteActor ! "Calling remote"
}
