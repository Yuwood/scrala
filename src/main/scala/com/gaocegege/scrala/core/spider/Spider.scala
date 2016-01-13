package com.gaocegege.scrala.core.spider

import com.gaocegege.scrala.core.common.response.impl.HttpResponse
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.gaocegege.scrala.core.middleware.filter.impl.DefaultFilter
import com.gaocegege.scrala.core.middleware.filter.Filter

/**
 * Spider Trait
 */
trait Spider {
  /** the start url */
  def startUrl: List[String]
  /** middleware-filter */
  def filter: Filter = new DefaultFilter
  def delay: Int = 0
  /** main function */
  def parse(response: HttpResponse): Unit
  /** run the engine */
  def begin(): Unit
  /** create a new request */
  def request(url: String, callback: (HttpResponse) => Unit): Unit

  val logger = Logger(LoggerFactory getLogger ("spider"))

  // for inherience, don't rewrite these, trust me
  private[this] var realWorker = 4
  def workerCount: Int = realWorker;
  def workerCount_=(v: Int) { realWorker = v; };

}
