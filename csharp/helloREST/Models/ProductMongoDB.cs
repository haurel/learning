using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace helloREST.Models
{
    public class ProductMongoDB
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id{ get; set; }
        public string Name { get; set; }
        public double Price { get; set; }
    }
}