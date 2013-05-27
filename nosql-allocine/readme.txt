mvn eclipse:eclipse gwt:generateAsync gwt:i18n gwt:eclipse package
Import existing project dans eclipse
Vérifier que l'encoding d'Eclipse est bien positioné  UTF-8

VM Arguments : -Dhttp.proxyHost=proxy.rennes.sii.fr -Dhttp.proxyPort=3128


#mongodb

mongod.exe --dbpath "D:\MongoDB Datafiles"
use movies
db.movies.drop();

// Les films ou l'acteur id = 1515 jouent triés par ordre alpha 
db.movies.find({'actors.person._id':1515}).sort({title:1})

	@Override
	public List<Movie> getMoviesWithActor(long id) {
		Query query = new Query(Criteria.where("actors.person._id").is(id));
		query.sort().on("title", Order.DESCENDING);
		getMongoOperations().find(query, Movie.class);
		return null;
	}


// Tous les id de films triés par ordre croissant 
db.movies.find({},{_id:1}).sort({_id:1})

m = function () {
    if (this.duration > 0) {
        emit("dur", this.duration);
    }
}

r = function (key, values) {
    var sum = 0;
    for (var i = 0; i < values.length; i++) {
        sum += values[i];
    }
    return sum / values.length;
}


res = db.movies.mapReduce(m,r, {out: { inline : 1}, query:{kinds:'Action'}});

res = db.runCommand({mapReduce : "movies", map : m, reduce : r, query : {kinds:'Action'}, out : {inline:1} })


#redis
pour supprimer la sauvegarde asynchrone sur PC
config set save ""



dbsize
flushall

#hbase
Pour hbase il faut configurer la VM en bridge
=> mofier le fichiers hosts des 2 machines
exemple : 10.6.192.68 	nbulteau-VirtualBox


