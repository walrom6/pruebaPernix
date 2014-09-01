
class ZipCodeInformationService
  include SoapObject

  wsdl 'http://www.webservicex.net/uszip.asmx?WSDL'

  def response_for(key)
    message[key]
  end

  private

  def message
    body
  end

end