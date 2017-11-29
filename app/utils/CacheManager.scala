package utils

import javax.inject.Inject

import play.api.cache._

import scala.reflect.ClassTag


class CacheManager @Inject()(cache: SyncCacheApi) {

  def set(key: String, value: Any) = cache.set(key, value)

  def get[T:ClassTag](key: String): Option[T] = cache.get[T](key)

}
