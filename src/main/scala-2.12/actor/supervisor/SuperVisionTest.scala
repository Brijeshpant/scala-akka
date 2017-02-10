package actor.supervisor

import akka.actor.{ActorRef, ActorSystem, Props}

/**
  * Created by bpant on 09/02/17.
  */
object SuperVisionTest extends App {

  private val system: ActorSystem = ActorSystem("new-system")
  private val supervisor: ActorRef = system.actorOf(Props[Supervisor])
  supervisor ! ThrowException
}
