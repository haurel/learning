using MongoDB.Driver;

namespace helloREST.Models
{
    public class MongoDBContext
    {
        private readonly IMongoDatabase _mongoDb;

        public MongoDBContext(){
            var client = new MongoClient(
              
            );

            _mongoDb = client.GetDatabase("Product");
        }

        public IMongoCollection<ProductMongoDB> Product{
            get{
                return _mongoDb.GetCollection<ProductMongoDB>("Product");
            }
        }
    }
}