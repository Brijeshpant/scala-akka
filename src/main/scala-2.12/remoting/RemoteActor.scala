package remoting

import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, AllForOneStrategy, SupervisorStrategy}

/**
  * Created by bpant on 09/02/17.
  */
class RemoteActor extends Actor {


  override def supervisorStrategy: SupervisorStrategy = AllForOneStrategy() {
    case ex: Exception => Restart
  }
  @scala.throws[Exception](classOf[Exception])
  override def preStart(): Unit = {

  }

  override def receive: Receive = {
    case msg: String => {
      println(s" $msg is received  remotely from $sender ")
      sender ! "hi"
    }

    case _ => println("Message is not identified")
  }
}
