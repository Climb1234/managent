export function uploadFile () {
  return process.env.BASE_API + '/file/upload'
}

export function downloadImg () {
  return process.env.BASE_API + '/file/downloadImg?fileName='
}
